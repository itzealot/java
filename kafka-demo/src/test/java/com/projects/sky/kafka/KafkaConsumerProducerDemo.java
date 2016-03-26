package com.projects.sky.kafka;

import com.projects.kafka.common.KafkaConfig;
import com.projects.kafka.consumer.KafkaConsumer;
import com.projects.kafka.producer.KafkaProducer;

public class KafkaConsumerProducerDemo {

	public static void main(String[] args) {
		KafkaProducer producerThread = new KafkaProducer(KafkaConfig.topic);
		new Thread(producerThread).start();

		KafkaConsumer consumerThread = new KafkaConsumer(KafkaConfig.topic);
		new Thread(consumerThread).start();
	}
}