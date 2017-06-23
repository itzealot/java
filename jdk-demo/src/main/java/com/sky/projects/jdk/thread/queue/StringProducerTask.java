package com.sky.projects.jdk.thread.queue;

import java.util.Random;

/**
 * 生产者，间隔不定长时间产生一个String并放入阻塞队列
 * 
 * @author zealot
 *
 */
public class StringProducerTask implements Runnable {

	private BlockingQueue<String> queue;
	private int maxSleepMs;

	public StringProducerTask(BlockingQueue<String> queue, int maxSleepMs) {
		super();
		this.queue = queue;
		this.maxSleepMs = maxSleepMs;
	}

	@Override
	public void run() {
		Random random = new Random();

		while (true) {
			try {
				queue.put(random.nextInt() + "");
				Thread.sleep(random.nextInt(maxSleepMs));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
