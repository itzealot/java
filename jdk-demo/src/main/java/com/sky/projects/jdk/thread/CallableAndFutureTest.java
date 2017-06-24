package com.sky.projects.jdk.thread;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureTest {

	public static void testOneTask() {
		// 1. 创建线程池(单)
		ExecutorService threadPool = Executors.newSingleThreadExecutor();

		// 2. 线程池执行任务并返回结果(任务实现 Callable<T> 接口)
		Future<String> future = threadPool.submit(() -> {
			Thread.sleep(1000);
			return "hello";
		});

		System.out.println("wait the result....");

		// 3. 使用Future.get 得到返回的结果
		try {
			System.out.println("get the result : " + future.get());
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}

		// 4. 结束线程池
		threadPool.shutdown();
	}

	public static void main(String[] args) {
		// testOneTask();
		testMoreTask();
	}

	public static void testMoreTask() {
		// 1. 创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(10);

		// 2. 创建CompletionService 实例，返回结果为Integer
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);

		// 3. 提交10个任务
		for (int i = 0; i < 10; i++) {
			final int seq = i;
			completionService.submit(() -> {
				Threads.sleep(new Random().nextInt(5000));
				return seq;
			});
		}

		// 4. 执行任务并返回返回结果
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println("results:" + completionService.take().get());
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
		}

		// 5. 结束线程池
		threadPool.shutdown();
	}
}
