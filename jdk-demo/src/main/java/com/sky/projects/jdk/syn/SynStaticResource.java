package com.sky.projects.jdk.syn;

import com.sky.projects.jdk.thread.Threads;

/**
 * 测试多个静态同步方法
 */
public class SynStaticResource {
	private static int value = 0;

	public static synchronized void add1() {
		System.out.println("ThreadName: " + Thread.currentThread().getName() + ", static value=" + value);
		value++;
		Threads.sleep(1000);
	}

	public synchronized static void add2() {
		System.out.println("ThreadName: " + Thread.currentThread().getName() + ", static value=" + value);
		value += 2;
		Threads.sleep(1000);
	}

	/**
	 * 等效方法，锁为 Class 对象
	 */
	public static void add3() {
		synchronized (SynStaticResource.class) {
			System.out.println("ThreadName: " + Thread.currentThread().getName() + ", static value=" + value);
			value += 3;
			Threads.sleep(1000);
		}
	}
}