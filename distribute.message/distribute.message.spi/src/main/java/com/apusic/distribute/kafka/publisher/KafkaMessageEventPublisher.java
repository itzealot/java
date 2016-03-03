package com.apusic.distribute.kafka.publisher;

import java.io.Serializable;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.apusic.distribute.message.model.MessageEvent;
import com.apusic.distribute.message.publisher.MessageEventPublisher;

/**
 * Kafka 消息生产并发送
 * 
 * @author zt
 *
 */
public class KafkaMessageEventPublisher<T extends Serializable> implements MessageEventPublisher<T> {
	private Producer<Long, MessageEvent<T>> producer;

	public KafkaMessageEventPublisher() {
		producer = getKafkaProducer();
	}

	/**
	 * 获取Kafka 生产者
	 * 
	 * @return
	 */
	private Producer<Long, MessageEvent<T>> getKafkaProducer() {
		Properties props = new Properties();

		props.put("bootstrap.servers", "172.20.129.154:9092,172.20.129.158:9092,172.20.129.159:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
		props.put("value.serializer", "com.apusic.distribute.kafka.serializable.MessageEventSerializer");

		Producer<Long, MessageEvent<T>> producer = new KafkaProducer<Long, MessageEvent<T>>(props);

		return producer;
	}

	@Override
	public void submitMessageEvent(MessageEvent<T> messageEvent) {
		producer.send(new ProducerRecord<Long, MessageEvent<T>>(messageEvent.getEventType(),
				messageEvent.getTime().getTime(), messageEvent));

		producer.flush();
		producer.close();
	}

}
