package com.sky.projects.jdk.thread.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * N个任务,平均分给M线程处理
 * 
 * @author zengtao
 *
 */
public class NTaskPerThread {

	// 任务数量
	int taskNum = 12;

	// 线程数量
	int threadNum = 3;

	// 任务集合
	List<Task> list = new ArrayList<NTaskPerThread.Task>();

	// 任务运行时间,用于比较不通线程数量的效率
	long total = 0;

	/**
	 * 测试方法
	 */
	public void test() {
		// 添加 taskNum 个任务
		for (int i = 0; i < taskNum; i++) {
			list.add(new Task(i));
		}

		/**
		 * 给每个线程分配任务,应list从索引0开始,所以分配任务编号从0开始.<br />
		 * num : 分配的任务数量
		 */
		int num = taskNum / threadNum;

		// 有剩余任务，分配任务数量加1
		if (taskNum % threadNum != 0) {
			num++;
		}

		// 依次给线程分配任务
		for (int i = 0; i < threadNum; i++) {
			int start = i * num;
			int end = Math.min((i + 1) * num, list.size());// 最后一个线程任务可能不够

			// 创建 threadNum 个线程，执行任务
			new TaskThread(start, end).start();
		}

	}

	/**
	 * 内部类， 任务，含编号属性
	 * 
	 * @author zengtao
	 *
	 */
	public class Task {
		private int n;

		public Task(int n) {
			this.n = n;
		}

		/**
		 * 任务的执行方法，即任务应该做什么事情
		 */
		public void run() {
			System.out.println("Task " + n);
			for (int i = 0; i < 100000000; i++) {
				@SuppressWarnings("unused")
				int s = 0;
				s += i;
			}

			System.out.println(Thread.currentThread().getName());
		}
	}

	/**
	 * 任务线程类
	 * 
	 * @author zengtao
	 *
	 */
	public class TaskThread extends Thread {
		int start;
		int end;

		public TaskThread(int start, int end) {
			this.start = start;
			this.end = end;

		}

		@Override
		public void run() {
			long s = System.currentTimeMillis();
			for (; start < end; start++) {

				// 执行任务的方法
				list.get(start).run();
			}

			// 计算时间结果累加
			total += (System.currentTimeMillis() - s);
			System.out.println("total time : " + total);
		}
	}
}