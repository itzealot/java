package com.sky.projects.jdk.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.util.NamedThreadFactory;

public class ScheduledThreadPoolTest {

	public static void main(String[] args) {
		final Class<ScheduledThreadPoolTest> clazz = ScheduledThreadPoolTest.class;

		// 系统结束前完成相关的清理工作
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			synchronized (clazz) {
				clazz.notify();
			}

			System.out.println("finish print....");
		}));

		long start = System.currentTimeMillis() / 1000 + 30;

		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor(new NamedThreadFactory("Test", true));

		service.scheduleAtFixedRate(() -> {
			System.out.println("print with every 2s");

			if (System.currentTimeMillis() / 1000 >= start) {
				System.exit(0); // 调用 System.exit 结束，会触发 addShutdownHook
			}

		}, 2 * 1000, 1000, TimeUnit.MILLISECONDS);

		synchronized (clazz) {
			try {
				clazz.wait();
			} catch (InterruptedException e) {
			}
		}
	}

}
