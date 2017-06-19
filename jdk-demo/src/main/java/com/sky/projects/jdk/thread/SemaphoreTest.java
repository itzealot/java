package com.sky.projects.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试 Semaphore 类，即同步信号量
 * 
 * @author zealot
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool(); // 线程池

		int permits = 3; // 创建含初始值为3的信号量
		final Semaphore semaphore = new Semaphore(permits);

		// 提交10个任务
		for (int i = 0; i < 10; i++) {
			threadPool.execute(() -> {// 执行任务
				try {
					semaphore.acquire(); // 申请信号量，即P操作

					printMsgAndPermits(permits, semaphore);

					semaphore.release(); // 释放信号量，即V操作

					printMsgAndPermits(permits, semaphore);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			});
		}

		// 关闭线程池
		threadPool.shutdown();
	}

	private static void printMsgAndPermits(int permits, final Semaphore semaphore) {
		System.out.println("Thread:" + Thread.currentThread().getName() + " 进入，当前已有 "
				+ (permits - semaphore.availablePermits()) + " 并发");
	}
}
