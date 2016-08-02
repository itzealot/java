package com.sky.projects.util.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sky.projects.util.base.Threads;
import com.sky.projects.util.thread.SkyAbstractThread;

public class SkyAbstractThreadTest extends SkyAbstractThread {
	@Override
	public void doRun() {
		System.out.println(Thread.currentThread().getName() + " sleep.....");
		Threads.sleep(1000);
	}

	public static void main(String[] args) {
		new Thread(new SkyAbstractThreadTest()).start();

		int size = 2;
		ExecutorService threadPool = Executors.newFixedThreadPool(size);
		for (int i = 0; i < size; i++) {
			threadPool.execute(new SkyAbstractThreadTest());
		}
	}

}
