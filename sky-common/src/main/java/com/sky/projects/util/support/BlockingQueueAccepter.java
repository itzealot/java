package com.sky.projects.util.support;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BlockingQueue 消息接收器
 * 
 * @author zealot
 *
 * @param <V>
 */
public class BlockingQueueAccepter<V> extends SkyAbstractAccepter<BlockingQueue<V>, V> {
	private static final Logger LOG = LoggerFactory.getLogger(BlockingQueueAccepter.class);

	public BlockingQueueAccepter(BlockingQueue<V> container) {
		super(container);
	}

	@Override
	public void accept(V message) {
		try {
			container.put(message);
		} catch (InterruptedException e) {
			LOG.error("put the message into BlockingQueue error, message:{}, {}", message, e);
		}
	}

	@Override
	public void accept(List<V> messages) {
		for (V message : messages) {
			this.accept(message);
		}
	}

}
