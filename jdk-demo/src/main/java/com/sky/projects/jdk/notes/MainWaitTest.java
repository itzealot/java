package com.sky.projects.jdk.notes;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sky.projects.jdk.thread.Threads;

import net.sf.ehcache.util.NamedThreadFactory;

public class MainWaitTest {

	public static void main(String[] args) throws InterruptedException {
		// shutdown then notify
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			synchronized (MainWaitTest.class) {
				MainWaitTest.class.notify();
			}
		}));

		ExecutorService service = Executors.newFixedThreadPool(3, new NamedThreadFactory("TestThread", true));

		for (int i = 0; i < 3; i++) {
			service.execute(() -> {
				Random random = new Random();

				while (true) {
					System.out.println(Thread.currentThread().getName() + " sleep...");
					int millis = random.nextInt(500) + 1000;
					Threads.sleep(millis);
				}
			});
		}

		// wait to finish
		synchronized (MainWaitTest.class) {
			MainWaitTest.class.wait();
		}
	}

}
