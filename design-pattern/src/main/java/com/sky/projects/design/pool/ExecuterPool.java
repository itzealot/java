package com.sky.projects.design.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.sky.projects.design.common.Threads;

/**
 * 自定义线程池，管理线程
 * 
 * @author zealot
 */
public class ExecuterPool {

	private static ExecuterPool pool = new ExecuterPool();
	private static final int MAX_POOL_SIZE = 10;

	private List<Poolable> threads; // 空闲的线程队列
	private int threadCounts;// 已有的线程总数
	private volatile boolean isShutdown = false; // 是否关闭标记
	private List<Runnable> runables = new LinkedList<>();

	private ExecuterPool() {
		this.threads = new Vector<>(MAX_POOL_SIZE); // 线程池大小
		threadCounts = 0;
	}

	/**
	 * 获取线程池实例
	 * 
	 * @return
	 */
	public static ExecuterPool getInstance() {
		return pool;
	}

	/**
	 * 将执行任务的线程放回线程池
	 * 
	 * @param poolable
	 */
	public synchronized void repool(Poolable poolable) {
		if (!isShutdown) {
			threads.add(poolable); // 拥有新空闲线程，唤醒阻塞的任务
		} else {
			pool.shutdown();
		}
	}

	public synchronized Poolable getExecuter() {
		if (!isShutdown) {
			if (threads.size() > 0) {// 有多于的空闲线程
				return threads.remove(threads.size() - 1);
			} else {
				return null;
			}
		} else {
			pool.shutdown();
			throw new RuntimeException("pool is shutdown...");
		}
	}

	public synchronized void addExecuter(Runnable runnable) {
		if (!isShutdown) {
			threadCounts++;
			Poolable p = new Executer(pool);
			threads.add(p); // 拥有新空闲线程，唤醒阻塞的任务
			p.start();// 启动执行线程
			p.submit(runnable);
		} else {
			pool.shutdown();
		}
	}

	/**
	 * 终止池中所有的任务线程
	 */
	public synchronized void shutdown() {
		isShutdown = true;
		for (int i = 0, len = threads.size(); i < len; i++) {
			threads.get(i).shutdown();
		}
	}

	/**
	 * 执行任务，即从池中选择一个任务线程执行任务
	 * 
	 * @param target
	 * @throws InterruptedException
	 */
	public synchronized void submit(Runnable runnable) {
		this.runables.add(runnable);

		if (threadCounts < MAX_POOL_SIZE) {
			addExecuter(runnable);
		}

		Poolable p = null;
		while (p == null) {
			p = getExecuter();
			System.out.println(p);
			Threads.sleep(1000);
			if (p != null) {
				if (p.isIdle()) {
					p.submit(runnable);
				} else {
					synchronized (p) {
						p.notifyAll();
					}
				}
			}
		}
	}

	public synchronized Runnable getTask() {
		return runables.size() > 0 ? runables.remove(0) : null;
	}

	/**
	 * 获取当前线程池的数量
	 * 
	 * @return
	 */
	public int getThreadCounts() {
		return threadCounts;
	}

}
