package com.sky.projects.design.pool;

import com.sky.projects.design.common.Threads;

/**
 * 任务线程
 * 
 * @author zealot
 */
public class TaskThread implements Runnable {

	private Object name;

	public TaskThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		Threads.sleep(100);
		System.out.println("ThreadName " + name + " finish tasks.");
	}

}
