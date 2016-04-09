package com.sky.projects.kafka.common;

/**
 * Kafka 配置信息
 * 
 * @author zt
 *
 */
public final class KafkaConfig {

	private KafkaConfig() {
	}

	public final static String zkConnect = "10.22.10.139:2181";

	public final static String groupId = "group1";

	public final static String topic = "topic1";

	public final static String kafkaServerURL = "10.22.10.139";

	public final static int kafkaServerPort = 9092;

	public final static int kafkaProducerBufferSize = 64 * 1024;

	public final static int connectionTimeOut = 20000;

	public final static int reconnectInterval = 10000;

	public final static String topic2 = "topic2";

	public final static String topic3 = "topic3";

	public final static String clientId = "SimpleConsumerDemoClient";
}