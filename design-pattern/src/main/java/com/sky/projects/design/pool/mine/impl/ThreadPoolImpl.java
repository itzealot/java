package com.sky.projects.design.pool.mine.impl;

import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

import com.sky.projects.design.pool.mine.ThreadPool;

public class ThreadPoolImpl implements ThreadPool {

	private final BlockingQueue<Runnable> taskQueue;
	private volatile boolean shutdown;
	private final Vector<Thread> threads;
	private final ThreadFactory factory;
	private final int initSize;
	private final int maxSize;

	public ThreadPoolImpl(int initSize, int maxSize, BlockingQueue<Runnable> taskQueue, ThreadFactory factory) {
		Objects.requireNonNull(taskQueue, "taskQueue can't be null");
		Objects.requireNonNull(factory, "factory can't be null");

		this.shutdown = false;
		this.maxSize = maxSize;
		this.threads = new Vector<>(maxSize);
		this.initSize = initSize;
		this.taskQueue = taskQueue;
		this.factory = factory;
	}

	@Override
	public void submit(Runnable runnable) {
		if (isShutdown()) { // 是否关闭
			throw new RuntimeException("thread pool is shutdown");
		}

		if (runnable == null) {
			throw new RuntimeException("runnable can't be null");
		}

		try {
			taskQueue.put(runnable);
		} catch (InterruptedException e) {
		}

		// 线程池大小还未达到初始值
		if (threads.size() < initSize && !taskQueue.isEmpty()) {
			if (threads.size() < initSize) {
				Thread thread = factory.newThread(new Worker());
				this.threads.add(thread);
				thread.start();
			}
		} else if (threads.size() < maxSize && !taskQueue.isEmpty()) {
			if (threads.size() < maxSize) {
				Thread thread = factory.newThread(new Worker());
				this.threads.add(thread);
				thread.start();
			}
		}
	}

	/**
	 * Worker
	 */
	private class Worker implements Runnable {

		public Worker() {
		}

		@Override
		public void run() {
			while (!ThreadPoolImpl.this.isShutdown() || !ThreadPoolImpl.this.taskQueue.isEmpty()) {
				try {
					Runnable task = ThreadPoolImpl.this.taskQueue.take();

					if (task != null)
						task.run();
				} catch (InterruptedException e) {
				}
			}
		}
	}

	class WrapperCallable<V> implements Callable<V> {
		private final Runnable runnable;
		private final V result;

		public WrapperCallable(Runnable runnable, V result) {
			Objects.requireNonNull(runnable, "runnable can't be null");
			this.runnable = runnable;
			this.result = result;
		}

		@Override
		public V call() throws Exception {
			runnable.run();
			return result;
		}

	}

	@Override
	public void shutdown() {
		this.shutdown = true;
	}

	@Override
	public boolean isShutdown() {
		return shutdown;
	}

}
