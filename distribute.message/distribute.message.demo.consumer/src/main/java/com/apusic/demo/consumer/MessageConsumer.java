package com.apusic.demo.consumer;

import com.apusic.demo.model.User;
import com.apusic.distribute.kafka.bus.KafkaMessageEventBus;
import com.apusic.distribute.message.bus.MessageEventBus;
import com.apusic.distribute.message.listener.MessageEventListener;
import com.apusic.distribute.message.model.MessageEvent;

/**
 * 消息消费者
 * 
 * @author zt
 *
 */
public class MessageConsumer implements MessageEventListener<User> {

	@Override
	public void handler(MessageEvent<User> eventMessage) {
		User usr = eventMessage.getMessage();
		System.out.println("Message: " + usr);
	}

	public static void main(String[] args) {
		MessageConsumer consumer = new MessageConsumer();
		MessageEventBus bus = new KafkaMessageEventBus();

		// 监听事件
		bus.addMessageEventListener("groupId-1", "event-1", consumer);
	}
}
