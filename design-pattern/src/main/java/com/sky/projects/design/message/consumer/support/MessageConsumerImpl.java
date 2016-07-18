package com.sky.projects.design.message.consumer.support;

import com.sky.projects.design.message.consumer.MessageConsumer;
import com.sky.projects.design.message.handler.MessageHandler;

public class MessageConsumerImpl implements MessageConsumer<String> {

	private String message;

	public MessageConsumerImpl() {
		this.message = "I am a message";
	}

	@Override
	public void consume(MessageHandler<String> handler) {
		System.out.println("get message and handle...........");
		handler.handle(message);
	}

}
