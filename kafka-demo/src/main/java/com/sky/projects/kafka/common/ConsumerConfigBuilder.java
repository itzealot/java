package com.sky.projects.kafka.common;

import java.util.Properties;

import kafka.consumer.ConsumerConfig;

/**
 * ConsumerConfig Builder
 * 
 * @author zt
 *
 */
public final class ConsumerConfigBuilder {
	private ConsumerConfigBuilder() {
	}

	/**
	 * 创建 ConsumerConfig 对象
	 * 
	 * @return
	 */
	public static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();

		props.put("zookeeper.connect", KafkaConfig.ZK_CONNECT);
		props.put("group.id", KafkaConfig.GROUO_ID);
		props.put("zookeeper.session.timeout.ms", "40000");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");

		return new ConsumerConfig(props);
	}
}
