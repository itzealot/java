package com.sky.projects.design.message.publisher;

/**
 * 消息发布接口
 * 
 * @author zt
 */
public interface MessagePublisher<T> {

	/**
	 * 接收消息并发布
	 * 
	 * @param message
	 */
	public void publish(T message);
}
