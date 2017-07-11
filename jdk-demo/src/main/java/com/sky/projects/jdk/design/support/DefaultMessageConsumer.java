package com.sky.projects.jdk.design.support;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sky.projects.jdk.design.AppException;
import com.sky.projects.jdk.design.MessageConsumer;

/**
 * DefaultMessageConsumer
 * 
 * @author zealot
 */
public class DefaultMessageConsumer implements MessageConsumer {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final BlockingQueue<String> blockingQueue;

	/**
	 * 
	 * @param blockingQueue
	 * @throws AppException
	 *             throw when blockingQueue is null
	 */
	public DefaultMessageConsumer(BlockingQueue<String> blockingQueue) throws AppException {
		checkNotNull(blockingQueue, "blockingQueue can't be null");

		this.blockingQueue = blockingQueue;
	}

	@Override
	public void consume(String message) {
		try {
			this.blockingQueue.put(message);
		} catch (InterruptedException e) {
			logger.error("consume fail, message:" + message, e);
			// Thread.currentThread().interrupt(); // 重新设置中断标记，用于是否响应中断
		}
	}

}
