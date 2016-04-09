package com.sky.projects.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sky.projects.kafka.common.ConsumerConfigBuilder;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * Kafka 消息消费者
 * 
 * @author zt
 *
 */
public class KafkaConsumer implements Runnable {
	private final ConsumerConnector consumer;

	private final String topic;

	public KafkaConsumer(String topic) {
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(ConsumerConfigBuilder.createConsumerConfig());

		this.topic = topic;
	}

	public void run() {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

		topicCountMap.put(topic, new Integer(1));

		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();

		while (it.hasNext()) {
			System.out.println("receive：" + new String(it.next().message()));

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}