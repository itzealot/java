package com.sky.projects.jdk.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @since 1.8
 * @author zealot
 *
 */
public class ThreadTest {

	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 10; i++) {
			service.execute(() -> {
				System.out.println(Thread.currentThread().getThreadGroup());
			});
		}

		// all Future holder
		final List<Future<?>> futures = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Runnable task = () -> {
				Threads.sleep(new Random().nextInt(500 + 10 * 1000));
				System.out.println(Thread.currentThread().getName() + " : finish");
			};

			futures.add(service.submit(task));
		}

		// 不再接收任务
		service.shutdown();

		int sum = 0;
		while (sum == futures.size()) {
			for (Future<?> future : futures) {
				if (future.isDone()) {
					sum++;
					System.out.println(future.get());
				}
			}
		}
	}

	public static void testThreadGroup() {
		ExecutorService service = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 10; i++) {
			service.execute(() -> {
				System.out.println(Thread.currentThread().getThreadGroup());
			});
		}

		// 不再接收任务
		service.shutdown();
	}

}
