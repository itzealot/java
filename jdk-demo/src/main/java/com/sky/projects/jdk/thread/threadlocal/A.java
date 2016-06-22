package com.sky.projects.jdk.thread.threadlocal;

public class A<T> {
	private ThreadLocal<T> local;

	public A(ThreadLocal<T> local) {
		this.local = local;
	}

	/**
	 * 从 ThreadLocal 中取数据，有多少个线程向 ThreadLocal 中放了数据，就会执行几次该方法，即每个线程放的数据都会
	 */
	public void get() {
		ThreadLocals.get(local, A.class.getSimpleName());
	}
}