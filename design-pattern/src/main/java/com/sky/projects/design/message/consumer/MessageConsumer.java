package com.sky.projects.design.message.consumer;

import com.sky.projects.design.message.handler.MessageHandler;

/**
 * 消息消费接口
 * 
 * @author zt
 */
public interface MessageConsumer<T> {

	/**
	 * 获取消息并调用消息处理接口消费消息
	 * 
	 * @param handler
	 */
	public void consume(MessageHandler<T> handler);
}
