package com.sky.projects.jdk.syn;

import com.sky.projects.jdk.thread.Threads;

/**
 * synchronized 关键字作用在实例方法上，锁为 this 对象
 */
public class SynResource {
	private int value = 0;

	public synchronized void add1() {
		System.out.println("ThreadName: " + Thread.currentThread().getName() + ", value=" + value);
		value++;
		Threads.sleep(1000);
	}

	public void add2() {
		synchronized (this) {
			System.out.println("ThreadName: " + Thread.currentThread().getName() + ", value=" + value);
			value += 2;
			Threads.sleep(1000);
		}
	}
}