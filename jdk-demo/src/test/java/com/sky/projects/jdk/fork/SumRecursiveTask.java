package com.sky.projects.jdk.fork;

import java.util.concurrent.RecursiveTask;

import com.sky.projects.jdk.thread.Threads;

/**
 * ForkJoin框架处理的任务基本都能使用递归处理，比如求斐波那契数列等，但递归算法的缺陷是：
 * 一只会只用单线程处理，二是递归次数过多时会导致堆栈溢出；ForkJoin解决了这两个问题，
 * 使用多线程并发处理，充分利用计算资源来提高效率，同时避免堆栈溢出发生。
 * 
 * 最佳应用场景：多核、多内存、可以分割计算再合并的计算密集型任务。
 * 
 * @author zealot
 *
 */
public class SumRecursiveTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = -175363011070711776L;

	private int n = 1;

	public SumRecursiveTask(int n) {
		this.n = n;
	}

	@Override
	protected Integer compute() {
		// 递归结束条件, i >= 100 时不进行划分
		if (n >= 1000) {
			return n;
		}

		Threads.sleep(100);
		SumRecursiveTask newTask = new SumRecursiveTask(n + 1);
		newTask.fork();

		return n + newTask.join();
	}

}