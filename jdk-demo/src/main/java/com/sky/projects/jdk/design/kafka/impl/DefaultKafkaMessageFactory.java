package com.sky.projects.jdk.design.kafka.impl;

import com.sky.projects.jdk.design.kafka.KafkaMessage;
import com.sky.projects.jdk.design.kafka.KafkaMessageFactory;

public class DefaultKafkaMessageFactory implements KafkaMessageFactory {

	@Override
	public KafkaMessage getKafkaMessage(String type, String[] contents) {
		return new DefaultKafkaMessage(type, contents);
	}
}
