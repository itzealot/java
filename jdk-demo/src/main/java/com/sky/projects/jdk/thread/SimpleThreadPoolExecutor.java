package com.sky.projects.jdk.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义 ThreadPoolExecutor 的实现类
 * 
 * @author zealot
 *
 */
public class SimpleThreadPoolExecutor extends ThreadPoolExecutor {

	public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.println("beforeExecute Thread name:" + t.getName() + ", id:" + t.getId());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Thread thread = Thread.currentThread();
		System.out.println("beforeExecute Thread name:" + thread.getName() + ", id:" + thread.getId() + ", poolSize:"
				+ this.getPoolSize());
	}
}
