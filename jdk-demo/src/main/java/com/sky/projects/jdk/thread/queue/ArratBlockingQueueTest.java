package com.sky.projects.jdk.thread.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArratBlockingQueueTest {

	public static void producerAndConsumer(int consumerSize, int producerSize) {
		ExecutorService consumers = Executors.newFixedThreadPool(consumerSize);
		ExecutorService producers = Executors.newFixedThreadPool(producerSize);

		BlockingQueue<String> queue = new ArratBlockingQueueUsingSynchronized<>();

		System.out.println("run consumers");
		for (int i = 0; i < consumerSize; i++) {
			consumers.submit(new ConsumerTask<>(queue, 1000));
		}

		System.out.println("run producers");
		for (int i = 0; i < producerSize; i++) {
			producers.submit(new StringProducerTask(queue, 1000));
		}
	}

	public static void main(String[] args) {
		// producerAndConsumer(3, 4);
		producerAndConsumer(4, 3);
	}
}
