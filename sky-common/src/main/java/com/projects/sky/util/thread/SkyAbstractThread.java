package com.projects.sky.util.thread;

import com.projects.sky.util.SkyRunnable;

/**
 * The Abstract Thread to execute doRun method circle
 * 
 * @author zealot
 */
public abstract class SkyAbstractThread implements SkyRunnable {
	protected volatile boolean running = true;

	public SkyAbstractThread() {
	}

	@Override
	public void run() {
		while (running) {
			doRun();
		}
	}

	public abstract void doRun();

	@Override
	public void stop() {
		this.running = false;
	}

}
