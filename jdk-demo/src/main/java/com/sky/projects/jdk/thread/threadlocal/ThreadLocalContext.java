package com.sky.projects.jdk.thread.threadlocal;

import java.util.Random;

import com.sky.projects.jdk.thread.Threads;

public class ThreadLocalContext {

	private int value;

	// 当前线程 ThreadLocal 信息
	private static final ThreadLocal<ThreadLocalContext> LOCAL = new ThreadLocal<ThreadLocalContext>() {
		@Override
		protected ThreadLocalContext initialValue() {
			return new ThreadLocalContext(new Random().nextInt());
		}
	};

	public ThreadLocalContext(int value) {
		this.value = value;
	}

	public static ThreadLocalContext getContext() {
		return LOCAL.get();
	}

	public static void removeContext() {
		LOCAL.remove();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static void main(String[] args) {
		Thread[] threads = new Thread[6];

		for (int i = 0; i < 6; i++) {
			threads[i] = new Thread(() -> {
				Random random = new Random();
				while (true) {
					if (random.nextBoolean()) { // is true, then read
						System.out.println("ThreadName:" + Thread.currentThread().getName() + ",read value:"
								+ ThreadLocalContext.LOCAL.get().getValue());
						Threads.sleep(1000);
					} else { // update
						int value = new Random().nextInt();
						ThreadLocalContext.LOCAL.get().setValue(value);
						System.out.println("ThreadName:" + Thread.currentThread().getName() + ",write value:" + value);
						Threads.sleep(1000);
					}
				}
			});

			threads[i].start();
		}
	}
}
