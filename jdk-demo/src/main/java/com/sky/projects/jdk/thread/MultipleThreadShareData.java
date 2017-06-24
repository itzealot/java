package com.sky.projects.jdk.thread;

import java.util.Random;

/**
 * 
 * @author zealot
 *
 */
public class MultipleThreadShareData implements Runnable {
	private static final Object LOCK = new Object();

	// 共享数据
	private int data = 0;

	@Override
	public void run() {
		while (true) {
			/*
			 * 同步代码块，因为是写操作，需要放到同步代码块内,此处的同步代码块为对数据先+1，而后进行读操作.
			 * 
			 * 同步代码块即保证多线程环境下对共享资源访问实现互斥访问
			 */
			synchronized (LOCK) {
				data++;
				show();
			}
			Threads.sleep(500 + new Random().nextInt(500));
		}
	}

	public void show() {
		System.out.println(Thread.currentThread().getName() + ":" + data);
	}
}