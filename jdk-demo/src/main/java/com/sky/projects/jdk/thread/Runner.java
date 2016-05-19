package com.sky.projects.jdk.thread;

import com.projects.sky.util.base.Threads;

public class Runner implements Runnable, AutoCloseable {

	private volatile boolean running = true;
	private volatile int counts = 1;

	public void run() {
		while (running) {
			doRun();
			counts++;
		}
	}

	public void doRun() {
		Threads.sleep(1000);
		System.out.println("print message..................");
	}

	public void close() throws Exception {
		this.running = false;
	}

	public int getCounts() {
		return counts;
	}
}
