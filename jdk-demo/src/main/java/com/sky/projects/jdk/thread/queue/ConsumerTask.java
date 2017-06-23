package com.sky.projects.jdk.thread.queue;

import java.util.Random;

/**
 * 消费者，间隔不定长时间从阻塞队列中消费一个数据
 * 
 * @author zealot
 *
 * @param <T>
 */
public class ConsumerTask<T> implements Runnable {

	private BlockingQueue<T> queue;
	private int maxSleepMs;

	public ConsumerTask(BlockingQueue<T> queue, int maxSleepMs) {
		super();
		this.queue = queue;
		this.maxSleepMs = maxSleepMs;
	}

	@Override
	public void run() {
		Random random = new Random();

		while (true) {
			try {
				queue.peek();
				Thread.sleep(random.nextInt(maxSleepMs));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
