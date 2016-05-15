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

	public final static String METADAT_BROKER_LIST = "localhost:9092";

	public final static String ZK_CONNECT = "localhost:2181";

	public final static String GROUO_ID = "group1";

	public final static String TOPIC = "topic1";

	public final static String KAFKA_SERVER_URL = "localhost";

	public final static int KAFKA_SERVER_PORT = 9092;

	public final static int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;

	public final static int CONNECTION_TIME_OUT = 20000;

	public final static int RECONNECT_INTERVAL = 10000;

	public final static String CLIENT_ID = "SimpleConsumerDemoClient";
}