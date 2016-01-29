package com.zt.test.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试Semaphore 类
 * 
 * @author zengtao
 *
 */
public class TestSemaphore {

	public static void main(String[] args) {

		// 线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();

		// 创建含初始值为3的信号量
		final Semaphore semaphore = new Semaphore(3);

		// 新建10个线程
		for (int i = 0; i < 10; i++) {

			Runnable runnable = new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					try {

						// 申请信号量，即P操作
						semaphore.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("Thread : "
							+ Thread.currentThread().getName() + " 进入，当前已有 "
							+ (3 - semaphore.availablePermits()) + " 并发");

					// 释放信号量，即V操作
					semaphore.release();

					System.out.println("Thread : "
							+ Thread.currentThread().getName() + " 已离开，当前已有 "
							+ (3 - semaphore.availablePermits()) + " 并发");
				}
			};

			// 执行线程池
			threadPool.execute(runnable);
		}

		// 关闭线程池
		threadPool.shutdown();
	}
}
