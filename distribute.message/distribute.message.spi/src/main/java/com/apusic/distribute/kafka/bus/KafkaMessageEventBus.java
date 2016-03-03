package com.apusic.distribute.kafka.bus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apusic.distribute.message.bus.MessageEventBus;
import com.apusic.distribute.message.listener.MessageEventListener;
import com.apusic.distribute.message.model.MessageEvent;

/**
 * Kafka事件处理实现类
 * 
 * @author zt
 *
 */
public class KafkaMessageEventBus implements MessageEventBus {

	private final static Logger log = LoggerFactory.getLogger(KafkaMessageEventBus.class);

	public KafkaMessageEventBus() {

	}

	private Consumer<Long, MessageEvent> getKafkaConsumer(String groupId) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "172.20.129.154:9092,172.20.129.158:9092,172.20.129.159:9092");
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("auto.offset.reset", "earliest");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
		props.put("value.deserializer", "com.apusic.distribute.kafka.serializable.MessageEventDeserializer");

		Consumer<Long, MessageEvent> consumer = new KafkaConsumer(props);
		return consumer;
	}

	@Override
	public <T extends Serializable> void addMessageEventListener(String groupId, List<String> eventTypes,
			MessageEventListener<T> eventListener) {
		Consumer<Long, MessageEvent> consumer = getKafkaConsumer(groupId);
		consumer.subscribe(eventTypes);
		while (true) {
			ConsumerRecords<Long, MessageEvent> records = consumer.poll(100);
			for (ConsumerRecord<Long, MessageEvent> record : records) {
				eventListener.handler(record.value());
			}
		}
	}

	@Override
	public <T extends Serializable> void addMessageEventListener(String groupId, String eventType,
			MessageEventListener<T> eventListener) {
		this.addMessageEventListener(groupId, Arrays.asList(eventType), eventListener);
	}
}
