package com.sky.projects.util.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * The Thread Util
 * 
 * @author zealot
 */
public final class Threads {
	private static final Logger LOG = LoggerFactory.getLogger(Threads.class);

	public static <T> T get(Future<T> future) {
		try {
			return future.get();
		} catch (Exception e) {
			LOG.error("get result from Future error.");
			return null;
		}
	}

	public static <T> T get(Future<T> future, long timeout, TimeUnit unit) {
		try {
			return future.get(timeout, unit);
		} catch (Exception e) {
			LOG.error("get result from Future error.");
			return null;
		}
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void sleep(long duration, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(duration));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 创建 ThreadFactory，ThreadFactory 可用于创建按照特定格式命名的现场名称，而非默认的 pool-x-thread-y
	 * 
	 * 如 nameFormat=mythread-%d，在用threaddump查看线程时特别有用
	 * 
	 * @param nameFormat
	 * @return
	 */
	public static ThreadFactory buildThreadFactory(String nameFormat) {
		return new ThreadFactoryBuilder().setNameFormat(nameFormat).build();
	}

	/**
	 * 按照ExecutorService JavaDoc示例代码编写的Graceful Shutdown方法. <br />
	 * --1). 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务. 如果超时, <br />
	 * --2). 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数. <br />
	 * --3). 如果仍然超時，則強制退出. 另对在shutdown时线程本身被调用中断做了处理.
	 * 
	 * @param pool
	 * @param shutdownTimeout
	 * @param shutdownNowTimeout
	 * @param unit
	 */
	public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout,
			TimeUnit unit) {
		pool.shutdown(); // Disable new tasks from being submitted

		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(shutdownTimeout, unit)) {
				pool.shutdownNow(); // Cancel currently executing tasks

				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(shutdownNowTimeout, unit)) {
					System.err.println("Pool did not terminated");
				}
			}
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 直接调用shutdownNow的方法, 有timeout控制.取消在workQueue中Pending的任务,并中断所有阻塞函数.
	 * 
	 * @param pool
	 * @param timeout
	 * @param timeUnit
	 */
	public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
		try {
			pool.shutdownNow();

			if (!pool.awaitTermination(timeout, timeUnit)) {
				System.err.println("Pool did not terminated");
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private Threads() {
	}
}
