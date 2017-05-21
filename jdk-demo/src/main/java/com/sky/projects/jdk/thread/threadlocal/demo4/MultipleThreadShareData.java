package com.sky.projects.jdk.thread.threadlocal.demo4;

import com.sky.projects.jdk.thread.Threads;

/**
 * 两个线程实现j减1,；两个线程实现j加1,一定要有两个Runnable，一个操作加，一个操作减.
 * 
 * @author zealot
 *
 */
public class MultipleThreadShareData {

	public static void main(String[] args) {
		Thread[] threads = new Thread[4];

		for (int i = 0; i < 2; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					while (true) {
						ShareData.increase();
						Threads.sleep(10);
					}
				}
			});
		}
		for (int i = 2; i < 4; i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					while (true) {
						ShareData.decreace();
						Threads.sleep(20);
					}
				}
			});
		}

		// 启动线程
		for (int i = 0; i < 4; i++) {
			threads[i].start();
		}
	}
}