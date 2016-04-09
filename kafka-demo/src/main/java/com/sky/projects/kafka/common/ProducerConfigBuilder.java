package com.sky.projects.kafka.common;

import java.util.Properties;

import kafka.producer.ProducerConfig;

/**
 * ProducerConfig Builder
 * 
 * @author zt
 *
 */
public final class ProducerConfigBuilder {
	private ProducerConfigBuilder() {
	}

	/**
	 * 创建 ProducerConfig 对象
	 * 
	 * @return
	 */
	public static ProducerConfig createConsumerConfig() {
		Properties props = new Properties();

		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("metadata.broker.list", "10.22.10.139:9092");

		return new ProducerConfig(props);
	}
}
