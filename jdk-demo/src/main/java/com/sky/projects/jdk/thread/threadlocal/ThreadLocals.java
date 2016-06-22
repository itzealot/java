package com.sky.projects.jdk.thread.threadlocal;

public final class ThreadLocals {

	public static <T> void get(ThreadLocal<T> local, String name) {
		T data = local.get();
		System.out.println(name + " from " + Thread.currentThread().getName() + " get data : " + data);
	}

	private ThreadLocals() {
	}
}
