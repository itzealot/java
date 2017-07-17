package com.sky.projects.jdk.thread;

public class Runner implements Runnable, AutoCloseable {

	private volatile boolean running = true;
	private volatile int counts = 1;

	@Override
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

	@Override
	public void close() {
		this.running = false;
	}

	public int getCounts() {
		return counts;
	}
}
