package com.sky.projects.jdk.design.kafka;

public interface KafkaMessageFactory {

	KafkaMessage getKafkaMessage(String dataType, String[] contents);

}
