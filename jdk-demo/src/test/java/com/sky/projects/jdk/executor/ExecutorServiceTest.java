package com.sky.projects.jdk.executor;

import java.util.concurrent.Executors;

import junit.framework.TestCase;

public class ExecutorServiceTest extends TestCase {

	public void test() {
		/*
		 * 返回固定大小的线程池，池中线程数量始终不变；有新任务提交时，线程池中若有空闲线程，则立即执行； <br>
		 * 若没有，则新的任务会被暂存到一个任务队列，代有线程空闲时，编处理在任务队列中的任务。
		 */
		Executors.newFixedThreadPool(2);

		/*
		 * 返回一个只有一个线程的线程池；若有多余任务提交，任务被暂存到一个任务队列，代有线程空闲时，编处理在任务队列中的任务。
		 */
		Executors.newSingleThreadExecutor();

		/*
		 * 返回一个可根据实际情况调整线程数量的线程池。线程池数量不确定，若有空闲线程，则复用；否则创建新的线程处理任务。
		 */
		Executors.newCachedThreadPool();

		/*
		 * 返回ScheduledExecutorService对象，线程池大小为1；扩展了定时功能，如在某个固定的延时之后执行，
		 * 或者周期性执行某个任务。
		 */
		Executors.newSingleThreadScheduledExecutor();

		/*
		 * 返回ScheduledExecutorService对象，线程池大小固定；扩展了定时功能，如在某个固定的延时之后执行，
		 * 或者周期性执行某个任务。
		 */
		Executors.newScheduledThreadPool(2);
	}

	static enum Instance {
		INSTANCE {
			@Override
			public void display() {
				System.out.println("instance");
			}
		};

		public void display() {
			System.out.println("a");
		}
	}
}
