package com.sky.projects.design.message.handler.support;

import com.sky.projects.design.message.handler.MessageHandler;

public class MessageHandlerImpl implements MessageHandler<String> {

	@Override
	public void handle(String message) {
		System.out.println("start handle message.............");
		System.out.println("after handle message: " + message + "...........");
		System.out.println("finish handle message.............");
	}

}
