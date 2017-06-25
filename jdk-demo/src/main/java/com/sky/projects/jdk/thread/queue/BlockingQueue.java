package com.sky.projects.jdk.thread.queue;

/**
 * BlockingQueue
 * 
 * @author zealot
 *
 * @param <T>
 */
public interface BlockingQueue<T> {

	/**
	 * 移除队列中队首元素，队列为空时阻塞
	 * 
	 * @return
	 */
	T peek();

	/**
	 * 往队列中 put 一条数据，队列满时阻塞
	 * 
	 * @param e
	 * @throws InterruptedException
	 */
	void put(T e) throws InterruptedException;
}
