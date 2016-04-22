package com.sky.projects.jdk;

import java.util.concurrent.RecursiveTask;

public class MyTask extends RecursiveTask<Integer> {
	private static final long serialVersionUID = -175363011070711776L;

	private int n = 1;

	public MyTask(int n) {
		this.n = n;
	}

	@Override
	protected Integer compute() {
		// 递归结束条件, i >= 100 时不进行划分
		if (n >= 1000) {
			return n;
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO
			e.printStackTrace();
		}

		MyTask newTask2 = new MyTask(n + 1);
		newTask2.fork();

		return n + newTask2.join();
	}

}