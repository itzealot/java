package com.sky.projects.test.demo;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnerTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// Waits until all parties have invoked await on this barrier.
		CyclicBarrier barrier = new CyclicBarrier(3);

		// 线程池
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new Thread(new Runner(barrier, "1号选手")));
		executor.submit(new Thread(new Runner(barrier, "2号选手")));
		executor.submit(new Thread(new Runner(barrier, "3号选手")));

		executor.shutdown();
	}
}