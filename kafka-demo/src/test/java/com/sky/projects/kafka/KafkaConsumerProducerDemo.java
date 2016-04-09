package com.sky.projects.kafka;

import com.sky.projects.kafka.common.KafkaConfig;
import com.sky.projects.kafka.consumer.KafkaConsumer;
import com.sky.projects.kafka.producer.KafkaProducer;

public class KafkaConsumerProducerDemo {

	public static void main(String[] args) {
		KafkaProducer producerThread = new KafkaProducer(KafkaConfig.topic);
		new Thread(producerThread).start();

		KafkaConsumer consumerThread = new KafkaConsumer(KafkaConfig.topic);
		new Thread(consumerThread).start();
	}
}