package com.apusic.distribute.kafka.publisher;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.apusic.distribute.message.model.MessageEvent;
import com.apusic.distribute.message.publisher.MessageEventPublisher;

/**
 * Created by a on 2016/1/19.
 */
public class KafkaMessageEventPublisher implements MessageEventPublisher {
	private Producer<Long, MessageEvent> producer;

	public KafkaMessageEventPublisher() {
		producer = getKafkaProducer();
	}

	private Producer<Long, MessageEvent> getKafkaProducer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "172.20.129.154:9092,172.20.129.158:9092,172.20.129.159:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
		props.put("value.serializer", "com.apusic.distribute.kafka.serializable.MessageEventSerializer");
		Producer<Long, MessageEvent> producer = new KafkaProducer(props);
		return producer;
	}

	public void submitMessageEvent(MessageEvent messageEvent) {
		producer.send(new ProducerRecord<Long, MessageEvent>(messageEvent.getEventType(),
				messageEvent.getTime().getTime(), messageEvent));
		producer.flush();
		producer.close();
	}

}
