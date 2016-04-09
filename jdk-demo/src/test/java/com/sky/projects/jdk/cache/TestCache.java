package com.sky.projects.jdk.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCache {

	// 根据线程池大小创建线程池
	private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	private static Cache cache = new Cache();

	public static void main(String[] args) {
		Runnable[] runnables = new Runnable[10];

		for (int i = 0; i < 5; i++) {
			runnables[i] = new GetThread(cache, "GetThread" + i);
			runnables[i + 5] = new PutThread(cache, "PutThread" + (i + 5));
			threadPool.execute(runnables[i]);
			threadPool.execute(runnables[i + 5]);
		}

		// shut down
		threadPool.shutdown();
	}
}
