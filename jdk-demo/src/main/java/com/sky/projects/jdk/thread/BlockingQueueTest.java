package com.sky.projects.jdk.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @since 1.8
 * @author zealot
 */
public class BlockingQueueTest {
	// 创建存放整数的阻塞队列，容量为3
	private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

	public static void main(String[] args) {
		// 2 个线程存数据(生产者)
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				Random random = new Random();

				while (true) {
					Threads.sleep(600 + random.nextInt(100));

					try {
						// 产生一个随机整数并放入到阻塞队列queue中
						queue.put(random.nextInt());
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}).start();
		}

		// 一个线程取数据(消费者)
		new Thread(() -> {
			while (true) {
				Threads.sleep(600);
				try {
					Integer data = queue.take(); // 取数据
					System.out.println("take data:" + data);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}).start();
	}
}
