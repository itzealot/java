package com.sky.projects.jdk.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	// 创建存放整数的阻塞队列，容量为3
	private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

	public static void main(String[] args) {
		// 2 个线程存数据(生产者)
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						Threads.sleep((long) Math.random() * 1000);
						System.out.println(Thread.currentThread().getName() + " : 准备放数据");

						try {
							// 产生一个随机整数并放入到阻塞队列queue中
							queue.put(new Random().nextInt());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						System.out.println(Thread.currentThread().getName() + " : 已放数据，队列目前有 " + queue.size() + " 个数据");
					}
				}
			}).start();
		}

		// 一个线程取数据(消费者)
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					Threads.sleep(600);
					System.out.println(Thread.currentThread().getName() + " : 准备取数据");

					try {
						// 取数据
						queue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println(Thread.currentThread().getName() + " : 已取数据，队列目前有 " + queue.size() + " 个数据");
				}
			}
		}).start();
	}
}
