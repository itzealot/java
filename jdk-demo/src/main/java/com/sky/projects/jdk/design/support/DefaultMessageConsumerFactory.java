package com.sky.projects.jdk.design.support;

import com.sky.projects.jdk.design.AppContext;
import com.sky.projects.jdk.design.MessageConsumer;
import com.sky.projects.jdk.design.MessageConsumerFactory;

/**
 * DefaultMessageConsumerFactory，可以通过配置全类名与反射的方式获取对应的 MessageConsumerFactory
 * 
 * @author zealot
 *
 */
public class DefaultMessageConsumerFactory implements MessageConsumerFactory {

	@Override
	public MessageConsumer getMessageConsumer(AppContext context) {
		return new DefaultMessageConsumer(context.getBlockingQueue());
	}

}
