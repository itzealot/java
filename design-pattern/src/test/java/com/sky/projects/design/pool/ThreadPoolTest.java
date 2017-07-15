package com.sky.projects.design.pool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;

import com.sky.projects.design.common.Threads;
import com.sky.projects.design.pool.mine.ThreadPool;
import com.sky.projects.design.pool.mine.impl.ThreadPoolImpl;

public class ThreadPoolTest {
	public static void main(String[] args) {
		ThreadPool pool = new ThreadPoolImpl(5, 5, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory());

		for (int i = 0; i < 50; i++) {
			pool.submit(() -> {
				Threads.sleep(new Random().nextInt(1000) + 2000);
				System.out.println("name:" + Thread.currentThread().getName() + " finish task");
			});
		}

		pool.shutdown();
	}

}
