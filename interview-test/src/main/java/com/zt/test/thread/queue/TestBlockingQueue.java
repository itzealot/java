package com.zt.test.thread.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestBlockingQueue {

	public static void main(String[] args) {

		// 创建存放整数的阻塞队列，容量为3
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

		// 2个线程存数据
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				public void run() {
					try {
						while (true) {
							Thread.sleep((long) Math.random() * 1000);

							System.out.println(Thread.currentThread().getName()
									+ " : 准备放数据");

							// 产生一个随机整数并放入到阻塞队列queue中
							Integer e = new Random().nextInt();

							// 放数据
							queue.put(e);

							System.out.println(Thread.currentThread().getName()
									+ " : 已放数据，队列目前有 " + queue.size() + " 个数据");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		// 取数据
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(600);

						System.out.println(Thread.currentThread().getName()
								+ " : 准备取数据");

						// 取数据
						queue.take();

						System.out.println(Thread.currentThread().getName()
								+ " : 已取数据，队列目前有 " + queue.size() + " 个数据");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
