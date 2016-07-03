package com.sky.projects.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程池.任务交给线程池，直接调用执行方法
 * 
 * @author zengtao
 *
 */
public class ThreadPoolTest {

	/**
	 * 测试固定线程池大小完成指定任务的线程池
	 */
	public static void test() {
		// 根据线程池大小创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		// 线程池执行10个任务，一个线程执行2个任务
		for (int j = 0; j < 10; j++) {
			final int task = j;
			// 线程池执行任务
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						Threads.sleep(20);

						System.out.println(Thread.currentThread().getName() + " : is run " + (i + 1) + " circle"
								+ ", Task " + task);
					}
				}
			});
		}

		// 等待任务执行完成，关闭线程池
		threadPool.shutdown();
		// 立即结束
		// threadPool.shutdownNow();
	}

	/**
	 * 测试缓冲线程池大小完成固定任务
	 */
	public static void testCache() {
		ExecutorService threadPool = Executors.newCachedThreadPool();

		// 线程池将扩充10个线程执行10个任务
		for (int j = 0; j < 10; j++) {
			final int task = j;
			// 线程池执行任务
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						Threads.sleep(20);

						System.out.println(Thread.currentThread().getName() + " : is run " + (i + 1) + " circle"
								+ ", Task " + task);
					}
				}
			});
		}

		// 任务执行完成，关闭线程池
		threadPool.shutdown();
	}

	/**
	 * 创建单一线程池, 总保证至少有一个线程.<br />
	 * 可以解决线程死掉后重新启动.<br />
	 */
	public static void testSingle() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();

		// 线程池将扩充10个线程执行10个任务
		for (int j = 0; j < 10; j++) {
			final int task = j;
			// 线程池执行任务
			threadPool.execute(new Runnable() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						Threads.sleep(20);

						System.out.println(Thread.currentThread().getName() + " : is run " + (i + 1) + " circle"
								+ ", Task " + task);
					}
				}
			});
		}

		// 任务执行完成，关闭线程池
		threadPool.shutdown();
	}

	/**
	 * 定时器线程池
	 */
	public static void testSchedule() {
		// 每隔多少时间
		int delay = 10;

		// 时间单位
		TimeUnit unit = TimeUnit.SECONDS;

		// 新建ScheduledExecutorService 线程池
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);

		threadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("boombing");
			}
		}, delay, unit);

		// 任务执行完成，关闭线程池
		threadPool.shutdown();
	}

	/**
	 * 定时器线程池
	 */
	public static void testSchedule2() {
		// 第一次启动隔多长时间
		int delay = 10;

		// 每隔多少时间
		int bombing = 2;

		// 时间单位
		TimeUnit unit = TimeUnit.SECONDS;

		// 新建ScheduledExecutorService 线程池
		ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);

		threadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("boombing");
			}
		}, delay, bombing, unit);
	}

	public static void main(String[] args) {
		// testCache();
		// testSingle();
		// testSchedule();
		testSchedule2();
	}
}
