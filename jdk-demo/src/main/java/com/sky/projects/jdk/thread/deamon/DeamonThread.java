package com.sky.projects.jdk.thread.deamon;

import com.sky.projects.jdk.thread.Threads;

public class DeamonThread implements Runnable {

	@Override
	public void run() {
		int count = 0;

		while (true) {
			System.out.println("DeamonThread count: " + count++);
			Threads.sleep(2000);
		}
	}
}