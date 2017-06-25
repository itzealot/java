package com.sky.projects.jdk.fork;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		System.out.println("The sum from 1 to 1000 is " + sumByRecursive(1, 1000));
		System.out.println("Time by recursive algorithm : " + (System.currentTimeMillis() - beginTime));

		beginTime = System.currentTimeMillis();
		System.out.println("The sum from 1 to 1000000000 is " + sumByForEach(1, 1000000000));
		System.out.println("Time by loop algorithm : " + (System.currentTimeMillis() - beginTime));

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		SumTask task = new SumTask(1, 1000000000);

		beginTime = System.currentTimeMillis();

		// 提交任务，返回结果
		Future<Long> result = forkJoinPool.submit(task);
		try {
			System.out.println("The sum from 1 to 1000000000 is " + result.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("time by ForkJoin algorithm:" + (System.currentTimeMillis() - beginTime));
	}

	/**
	 * sum of [start, end] by for each
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static long sumByForEach(long start, long end) {
		long s = 0l;
		for (long i = start; i <= end; i++) {
			s += i;
		}
		return s;
	}

	/**
	 * sum of [start, end] by recursive
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private static long sumByRecursive(long start, long end) {
		if (end > start) {
			return end + sumByRecursive(start, end - 1);
		} else {
			return start;
		}
	}

	/**
	 * 求[start, end]区间内的元素和
	 */
	@SuppressWarnings("serial")
	static class SumTask extends RecursiveTask<Long> {
		private static final int THRESHOLD = 10000;
		private int start;
		private int end;

		/**
		 * 求[start, end]区间内的元素和
		 * 
		 * @param start
		 * @param end
		 */
		public SumTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public Long compute() {
			if (end - start <= THRESHOLD) { // 距离在简单求和的范围内
				return sumByForEach(start, end);
			} else { // 划分子集求和
				int middle = (start + end) / 2;
				SumTask leftTask = new SumTask(start, middle);
				SumTask rightTask = new SumTask(middle + 1, end);

				/*
				 * 任务放入队列并安排异步执行，一个任务应该只调用一次fork()函数，除非已经执行完毕并重新初始化
				 */
				leftTask.fork();
				rightTask.fork();

				// 等待计算完成并返回结果之和
				return leftTask.join() + rightTask.join();
			}
		}
	}
}