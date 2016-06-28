package com.sky.projects.spark.streaming.common;

import java.util.HashMap;
import java.util.Map;

public class KafkaUtil {

	public static Map<Integer, Long> getLastestOffset(String topic, String bootstrapServers) {
		return null;
	}

	public static Map<Integer, Long> getEarliestOffset(String topic, String bootstrapServers) {
		return null;
	}

	public static Map<String, String> getKafkaConf(String bootstrapServers, String zkQuorum, String groupId) {
		Map<String, String> kafkaParams = new HashMap<String, String>();

		// spark1.3.0必须配置bootstrap.servers or metadata.broker.list
		kafkaParams.put("bootstrap.servers", bootstrapServers);
		kafkaParams.put("zookeeper.connect", zkQuorum);
		kafkaParams.put("zookeeper.connection.timeout.ms", "10000");
		kafkaParams.put("zookeeper.session.timeout.ms", "6000");
		kafkaParams.put("zookeeper.sync.time.ms", "2000");
		kafkaParams.put("group.id", groupId);
		// kafkaParams.put("consumer.id", "SimpleSparkConsumer");
		kafkaParams.put("auto.offset.reset", "smallest");
		kafkaParams.put("auto.commit.interval.ms", "1000");
		kafkaParams.put("fetch.message.max.bytes", "41943040");
		kafkaParams.put("replica.fetch.max.bytes", "41943040");
		// kafkaParams.put("num.consumer.fetchers", "4");

		return kafkaParams;
	}
}
