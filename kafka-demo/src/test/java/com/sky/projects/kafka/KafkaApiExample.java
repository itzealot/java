package com.sky.projects.kafka;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.BrokerEndPoint;
import kafka.common.ErrorMapping;
import kafka.common.TopicAndPartition;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;

public class KafkaApiExample {
	private List<String> brokers = new ArrayList<String>();

	public KafkaApiExample() {
		brokers = new ArrayList<String>();
	}

	public static void main(String args[]) {
		KafkaApiExample example = new KafkaApiExample();

		// 最大读取消息数量
		long maxReads = 3;

		// 要订阅的topic
		String topic = "mytopic";

		// 要查找的分区
		int partition = 0;

		// broker节点的IP
		List<String> seeds = new ArrayList<String>();
		seeds.add("192.168.4.30");
		seeds.add("192.168.4.31");
		seeds.add("192.168.4.32");

		// 端口
		int port = 9092;

		try {
			example.execute(maxReads, topic, partition, seeds, port);
		} catch (Exception e) {
			System.out.println("Oops:" + e);
			e.printStackTrace();
		}
	}

	public void execute(long maxReads, String topic, int partition, List<String> brokers, int port) throws Exception {
		PartitionMetadata metaData = findLeader(brokers, port, topic, partition);

		if (metaData == null) {
			System.out.println("Can't find metadata for Topic and Partition. Exiting");
			return;
		}

		if (metaData.leader() == null) {
			System.out.println("Can't find Leader for Topic and Partition. Exiting");
			return;
		}

		String leadBroker = metaData.leader().host();
		String clientName = "Client_" + topic + "_" + partition;

		SimpleConsumer consumer = new SimpleConsumer(leadBroker, port, 100000, 64 * 1024, clientName);
		long readOffset = getLastOffset(consumer, topic, partition, kafka.api.OffsetRequest.EarliestTime(), clientName);
		int numErrors = 0;
		while (maxReads > 0) {
			if (consumer == null) {
				consumer = new SimpleConsumer(leadBroker, port, 100000, 64 * 1024, clientName);
			}
			FetchRequest req = new FetchRequestBuilder().clientId(clientName)
					.addFetch(topic, partition, readOffset, 100000).build();
			FetchResponse fetchResponse = consumer.fetch(req);

			if (fetchResponse.hasError()) {
				numErrors++;
				// Something went wrong!
				short code = fetchResponse.errorCode(topic, partition);
				System.out.println("Error fetching data from the Broker:" + leadBroker + " Reason: " + code);
				if (numErrors > 5)
					break;
				if (code == ErrorMapping.OffsetOutOfRangeCode()) {
					// We asked for an invalid offset. For simple case ask for
					// the last element to reset
					readOffset = getLastOffset(consumer, topic, partition, kafka.api.OffsetRequest.LatestTime(),
							clientName);
					continue;
				}
				consumer.close();
				consumer = null;
				leadBroker = findNewLeader(leadBroker, topic, partition, port);
				continue;
			}
			numErrors = 0;

			long numRead = 0;
			for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(topic, partition)) {
				long currentOffset = messageAndOffset.offset();
				if (currentOffset < readOffset) {
					System.out.println("Found an old offset: " + currentOffset + " Expecting: " + readOffset);
					continue;
				}

				readOffset = messageAndOffset.nextOffset();
				ByteBuffer payload = messageAndOffset.message().payload();

				byte[] bytes = new byte[payload.limit()];
				payload.get(bytes);
				System.out.println(String.valueOf(messageAndOffset.offset()) + ": " + new String(bytes, "UTF-8"));
				numRead++;
				maxReads--;
			}

			if (numRead == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
				}
			}
		}
		if (consumer != null)
			consumer.close();
	}

	public static long getLastOffset(SimpleConsumer consumer, String topic, int partition, long whichTime,
			String clientName) {
		TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
		Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
		requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
		kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo,
				kafka.api.OffsetRequest.CurrentVersion(), clientName);
		OffsetResponse response = consumer.getOffsetsBefore(request);

		if (response.hasError()) {
			System.out.println(
					"Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition));
			return 0;
		}

		long[] offsets = response.offsets(topic, partition);
		return offsets[0];
	}

	/**
	 * 找一个leader broker
	 * 
	 * @param oldLeader
	 * @param topic
	 * @param partition
	 * @param port
	 * @return
	 * @throws Exception
	 */
	private String findNewLeader(String oldLeader, String topic, int partition, int port) throws Exception {
		for (int i = 0; i < 3; i++) {
			boolean goToSleep = false;
			PartitionMetadata metadata = findLeader(brokers, port, topic, partition);
			if (metadata == null) {
				goToSleep = true;
			} else if (metadata.leader() == null) {
				goToSleep = true;
			} else if (oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {
				// first time through if the leader hasn't changed give
				// ZooKeeper a second to recover
				// second time, assume the broker did recover before failover,
				// or it was a non-Broker issue
				//
				goToSleep = true;
			} else {
				return metadata.leader().host();
			}
			if (goToSleep) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
				}
			}
		}
		System.out.println("Unable to find new leader after Broker failure. Exiting");
		throw new Exception("Unable to find new leader after Broker failure. Exiting");
	}

	/**
	 * 获取指定Topic partition的元数据
	 * 
	 * @param brokers
	 * @param port
	 * @param topic
	 * @param partition
	 * @return
	 */
	private PartitionMetadata findLeader(List<String> brokers, int port, String topic, int partition) {
		PartitionMetadata metaData = null;

		loop: for (String seed : brokers) {
			SimpleConsumer consumer = null;

			try {
				consumer = new SimpleConsumer(seed, port, 100000, 64 * 1024, "leaderLookup");

				List<String> topics = Collections.singletonList(topic);

				TopicMetadataRequest req = new TopicMetadataRequest(topics);
				kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);

				List<TopicMetadata> metaDatas = resp.topicsMetadata();

				for (TopicMetadata item : metaDatas) {
					for (PartitionMetadata part : item.partitionsMetadata()) {
						if (part.partitionId() == partition) {
							metaData = part;
							break loop;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Error communicating with Broker [" + seed + "] to find Leader for [" + topic + ", "
						+ partition + "] Reason: " + e);
			} finally {
				if (consumer != null)
					consumer.close();
			}
		}

		if (metaData != null) {
			brokers.clear();
			List<BrokerEndPoint> endpoints = metaData.replicas();

			for (BrokerEndPoint point : endpoints) {
				brokers.add(point.host());
			}
		}

		return metaData;
	}
}