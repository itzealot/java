package com.sky.projects.design.message.publisher.support;

import com.sky.projects.design.message.publisher.MessagePublisher;

public class MessagePublisherImpl implements MessagePublisher<String> {

	@Override
	public void publish(String message) {
		System.out.println("publish message: " + message);
	}

}
