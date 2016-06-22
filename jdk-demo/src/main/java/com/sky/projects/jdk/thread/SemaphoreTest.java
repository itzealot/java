package com.sky.projects.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试 Semaphore 类，即同步信号量
 * 
 * @author zengtao
 *
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		// 线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();

		// 创建含初始值为3的信号量
		final Semaphore semaphore = new Semaphore(3);

		// 新建10个线程
		for (int i = 0; i < 10; i++) {
			// 执行线程池
			threadPool.execute(new Runnable() {
				public void run() {
					try {
						// 申请信号量，即 P 操作
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("Thread : " + Thread.currentThread().getName() + " 进入，当前已有 "
							+ (3 - semaphore.availablePermits()) + " 并发");

					// 释放信号量，即V操作
					semaphore.release();

					System.out.println("Thread : " + Thread.currentThread().getName() + " 已离开，当前已有 "
							+ (3 - semaphore.availablePermits()) + " 并发");
				}
			});
		}

		// 关闭线程池
		threadPool.shutdown();
	}
}
