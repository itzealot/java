package com.sky.projects.util.base;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BlockingQueue Util
 * 
 * @author zealot
 */
public final class BlockingQueues {
	private static final Logger LOG = LoggerFactory.getLogger(BlockingQueues.class);

	/**
	 * put an object into BlockingQueue
	 * 
	 * 添加一个对象到阻塞队列 BlockingQueue
	 * 
	 * @param queue
	 * @param obj
	 */
	public static <T> void put(BlockingQueue<T> queue, T obj) {
		try {
			queue.put(obj);
		} catch (InterruptedException e) {
			LOG.error("put object into BlockingQueue error, obj:{}, {}", String.valueOf(obj), e);
		}
	}

	/**
	 * put objects into BlockingQueue
	 * 
	 * 批量添加对象到阻塞队列 BlockingQueue
	 * 
	 * @param queue
	 * @param obj
	 */
	public static <T> void put(BlockingQueue<T> queue, Collection<T> objs) {
		for (T obj : objs) {
			put(queue, obj);
		}
	}

	/**
	 * take an object from BlockingQueue
	 * 
	 * 从阻塞队列 BlockingQueue 中取出一个对象
	 * 
	 * @param queue
	 * @return
	 */
	public static <T> T take(BlockingQueue<T> queue) {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			LOG.error("take object from BlockingQueue error.", e);
			return null;
		}
	}

	/**
	 * take objects from BlockingQueue but max elements limit
	 * 
	 * 根据设置的最大批量参数从阻塞队列 BlockingQueue 取数据，并存入 Collection 中
	 * 
	 * @param queue
	 * @param c
	 * @param maxElements
	 */
	public static <T> void drainTo(BlockingQueue<T> queue, Collection<T> c, int maxElements) {
		try {
			queue.drainTo(c, maxElements);
		} catch (Exception e) {
			LOG.error("drainTo objects into Collection from BlockingQueue error, maxElements:{}, {}", maxElements, e);
		}
	}

	/**
	 * take objects from BlockingQueue
	 * 
	 * 批量从阻塞队列 BlockingQueue 取数据，并存入 Collection 中
	 * 
	 * @param queue
	 * @param c
	 */
	public static <T> void drainTo(BlockingQueue<T> queue, Collection<T> c) {
		try {
			queue.drainTo(c);
		} catch (Exception e) {
			LOG.error("drainTo objects into Collection from BlockingQueue error.", e);
		}
	}

	private BlockingQueues() {
	}
}
