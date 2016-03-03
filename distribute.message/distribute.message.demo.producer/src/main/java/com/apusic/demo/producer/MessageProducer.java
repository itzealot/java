package com.apusic.demo.producer;

import com.apusic.demo.model.Role;
import com.apusic.demo.model.User;
import com.apusic.distribute.kafka.publisher.KafkaMessageEventPublisher;
import com.apusic.distribute.message.model.MessageEvent;
import com.apusic.distribute.message.publisher.MessageEventPublisher;

/**
 * 消息生产者
 * 
 * @author zt
 *
 */
public class MessageProducer {

	public static void main(String[] args) {
		MessageEventPublisher<User> pub = new KafkaMessageEventPublisher<User>();

		MessageEvent<User> me = new MessageEvent<User>();
		me.setEventType("event-1");

		Role role = new Role();
		role.setId(1);
		role.setName("Admin");
		role.setDescription("Super administrator");

		User user = new User();
		user.setRole(role);
		user.setName("Scott");
		user.setPassowrd("thefox");
		user.setStatus("enable");

		me.setMessage(user);

		pub.submitMessageEvent(me);
	}
}
