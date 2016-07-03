package com.sky.projects.jdk.thread.deamon;

import com.sky.projects.jdk.thread.Threads;

public class NoneDeamonThread implements Runnable {

	@Override
	public void run() {
		int i = 0;

		while (i < 10) {
			System.out.println("NoneDeamonThread index: " + i++);
			Threads.sleep(1000);
		}

		System.out.println("==> NoneDeamonThread ending...");
	}
}