package com.sky.projects.jdk.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileTest {

	static interface SkyRunnable extends Runnable {
		void shutdown();
	}

	static class NotVolatileRunner implements SkyRunnable {
		private boolean running = true;

		@Override
		public void run() {
			while (running) {
				System.out.println("ThreadName:" + Thread.currentThread().getName() + " do");
				Threads.sleep(10);
			}
			System.out.println("ThreadName:" + Thread.currentThread().getName() + " shutdown...");
		}

		@Override
		public void shutdown() {
			this.running = false;
		}
	}

	static class VolatileRunner implements SkyRunnable {
		private volatile boolean running = true;

		@Override
		public void run() {
			while (running) {
				System.out.println("ThreadName:" + Thread.currentThread().getName() + " do");
				Threads.sleep(10);
			}
			System.out.println("ThreadName:" + Thread.currentThread().getName() + " shutdown...");
		}

		@Override
		public void shutdown() {
			this.running = false;
		}
	}

	public static void main(String[] args) {
		testNotVolatileHolder();
		// testVolatileHolder();
	}

	static void testNotVolatileHolder() {
		SkyRunnable holder = new NotVolatileRunner();
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(holder);

		Threads.sleep(1000);
		holder.shutdown();

		service.shutdown();
	}

	static void testVolatileHolder() {
		SkyRunnable holder = new VolatileRunner();
		ExecutorService service = Executors.newFixedThreadPool(1);
		ExecutorService change = Executors.newFixedThreadPool(1);
		service.execute(holder);

		change.execute(() -> {
			Threads.sleep(1000);
			holder.shutdown();
		});

		service.shutdown();
		change.shutdown();
	}

}
