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

import com.apusic.distribute.kafka.common.KafkaConf;
import com.apusic.distribute.message.bus.MessageEventBus;
import com.apusic.distribute.message.listener.MessageEventListener;
import com.apusic.distribute.message.model.MessageEvent;

/**
 * Kafka 事件处理实现类
 * 
 * @author zt
 *
 */
public class KafkaMessageEventBus implements MessageEventBus {

	private final static Logger log = LoggerFactory.getLogger(KafkaMessageEventBus.class);

	public KafkaMessageEventBus() {
		log.info("create KafkaMessageEventBus.");
	}

	/**
	 * 根据 groupId 获取 Kafka 消费者
	 * 
	 * @param groupId
	 * @return
	 */
	private <T extends Serializable> Consumer<Long, MessageEvent<T>> getKafkaConsumer(String groupId) {
		Properties props = new Properties();

		props.put("bootstrap.servers", KafkaConf.BOOTSTRAP_SERVERS);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("auto.offset.reset", "earliest");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
		props.put("value.deserializer", "com.apusic.distribute.kafka.serializable.MessageEventDeserializer");

		return new KafkaConsumer<Long, MessageEvent<T>>(props);
	}

	@Override
	public <T extends Serializable> void addMessageEventListener(String groupId, List<String> eventTypes,
			MessageEventListener<T> eventListener) {
		Consumer<Long, MessageEvent<T>> consumer = getKafkaConsumer(groupId);

		// 根据消息类型订阅消息
		consumer.subscribe(eventTypes);

		while (true) {
			ConsumerRecords<Long, MessageEvent<T>> records = consumer.poll(100);
			for (ConsumerRecord<Long, MessageEvent<T>> record : records) {
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
