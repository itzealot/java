package com.sky.projects.jdk.thread;

import com.projects.sky.util.base.Threads;

/**
 * 线程A,B间的通信，形成互斥访问
 * 
 * @author zt
 *
 */
public class TwoThread {
	// 锁为静态成员变量，持有的是相同的对象
	private static Object lock = new Object();

	// 指定由谁运行
	private static boolean flag = false;

	public static void main(String[] args) {
		new Thread() {
			public void run() {
				while (true) {
					synchronized (lock) {// 同步代码块，互斥访问
						if (flag) {
							System.out.println("Thread-1");
							Threads.sleep(1000);

							flag = false;
							lock.notify();// 唤醒等待的线程

							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				while (true) {
					synchronized (lock) {
						if (!flag) {
							System.out.println("Thread-2");
							Threads.sleep(1000);

							flag = true;
							lock.notify();// 唤醒等待线程

							try {
								lock.wait();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}.start();
	}
}