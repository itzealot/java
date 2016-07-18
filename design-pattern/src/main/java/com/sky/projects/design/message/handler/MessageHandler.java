package com.sky.projects.design.message.handler;

/**
 * 消息处理器
 * 
 * @author zt
 */
public interface MessageHandler<T> {

	/**
	 * 接收消息并进行处理
	 * 
	 * @param message
	 */
	public void handle(T message);

}
