package com.sky.projects.jdk.thread.threadlocal;

public class B<T> {
	private ThreadLocal<T> local;

	public B(ThreadLocal<T> local) {
		this.local = local;
	}

	public void get() {
		ThreadLocals.get(local, B.class.getSimpleName());
	}
}