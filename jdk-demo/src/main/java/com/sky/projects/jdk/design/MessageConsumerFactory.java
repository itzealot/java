package com.sky.projects.jdk.design;

/**
 * MessageExecutorFactory
 * 
 * @author zealot
 */
public interface MessageConsumerFactory {

	/**
	 * 根据上下文参数获取 MessageConsumer
	 * 
	 * @param context
	 * @return
	 */
	MessageConsumer getMessageConsumer(AppContext context);
}
