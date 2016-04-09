package com.sky.projects.kafka.producer;

import com.sky.projects.kafka.common.ProducerConfigBuilder;

import kafka.producer.KeyedMessage;

/**
 * Kafka 消息生产者
 * 
 * @author zt
 *
 */
public class KafkaProducer implements Runnable {
	// kafka producer
	private final kafka.javaapi.producer.Producer<Integer, String> producer;

	private final String topic;

	public KafkaProducer(String topic) {
		producer = new kafka.javaapi.producer.Producer<Integer, String>(ProducerConfigBuilder.createConsumerConfig());

		this.topic = topic;
	}

	public void run() {
		int messageNo = 1;

		while (true) {
			String messageStr = new String("Message_" + messageNo);

			System.out.println("Send:" + messageStr);

			producer.send(new KeyedMessage<Integer, String>(topic, messageStr));

			messageNo++;

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO
				e.printStackTrace();
			}
		}
	}
}