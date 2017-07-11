package com.sky.projects.jdk.thread.threadlocal.demo4;

public class ShareData {
	// 全局变量，实现共享
	private static int j = 0;

	// 全局锁，实现共享
	private static Object lock = new Object();

	public static void decreace() {
		synchronized (lock) {
			System.out.println("decrease, Name : " + Thread.currentThread().getName() + " : j = " + --j);
		}
	}

	public static void increase() {
		synchronized (lock) {
			System.out.println("increase, Name : " + Thread.currentThread().getName() + " : j = " + ++j);
		}
	}

	public static void show() {
		System.out.println("Name : " + Thread.currentThread().getName() + " : j = " + j);
	}
}