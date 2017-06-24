package com.sky.projects.jdk.thread.queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ArratBlockingQueueTest
 * 
 * @author zealot
 *
 */
public class ArratBlockingQueueTest {

	/**
	 * 创建生产者与消费者，使用lambda表达式，需要Jdk1.8支持
	 * 
	 * @since 1.8
	 * @param consumerSize
	 *            消费者个数
	 * @param producerSize
	 *            生产者个数
	 */
	public static void producerAndConsumer(int consumerSize, int producerSize) {
		// 消费者创建固定大小的线程池
		ExecutorService consumers = Executors.newFixedThreadPool(consumerSize);
		// 生产者创建固定大小的线程池
		ExecutorService producers = Executors.newFixedThreadPool(producerSize);

		BlockingQueue<String> queue = new ArratBlockingQueueUsingSynchronizedThis<>();

		System.out.println(String.format("run consumers, size:%d", consumerSize));
		for (int i = 0; i < consumerSize; i++) {
			consumers.submit(() -> {
				Random random = new Random();

				while (true) {
					try {
						queue.peek();
						Thread.sleep(random.nextInt(1000));
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			});
		}

		System.out.println(String.format("run producers, size:%d", producerSize));
		for (int i = 0; i < producerSize; i++) {
			producers.submit(() -> {
				Random random = new Random();

				while (true) {
					try {
						queue.put(random.nextInt() + "");
						Thread.sleep(random.nextInt(1000));
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		producerAndConsumer(3, 3);
	}
}
