package com.sky.projects.jdk.thread;

import com.projects.sky.util.base.Threads;
import com.projects.sky.util.common.Closeables;

public class RunnerClose implements Runnable {

	private Runner runner;
	private int counts;

	public RunnerClose(Runner runner, int counts) {
		this.runner = runner;
		this.counts = counts;
	}

	@Override
	public void run() {
		while (true) {
			Threads.sleep(500);

			System.out.println("counts is : " + runner.getCounts());

			if (runner.getCounts() >= counts) {
				Closeables.close(runner);
				break;
			}
		}
	}

}
