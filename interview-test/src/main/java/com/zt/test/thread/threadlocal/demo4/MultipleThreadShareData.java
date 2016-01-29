package com.zt.test.thread.threadlocal.demo4;

/**
 * 两个线程实现j减1,；两个线程实现j加1.<br />
 * 一定要有两个Runnable，一个操作加，一个操作减.<br />
 * 
 * @author zengtao
 *
 */
public class MultipleThreadShareData {

	public static void main(String[] args) {
		Thread[] threads = new Thread[4];

		for (int i = 0; i < 2; i++) {
			threads[i] = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						ShareData.increase();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}
		for (int i = 2; i < 4; i++) {
			threads[i] = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						ShareData.decreace();
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

class ShareData {
	// 全局变量，实现共享
	public static int j = 0;

	// 全局锁，实现共享
	public static Object lock = new Object();

	/**
	 * j--
	 */
	public static void decreace() {
		synchronized (lock) {
			j--;
			System.out.println("decrease, Name : "
					+ Thread.currentThread().getName() + " : j = " + j);
		}
	}

	/**
	 * j++
	 */
	public static void increase() {
		synchronized (lock) {
			j++;
			System.out.println("increase, Name : "
					+ Thread.currentThread().getName() + " : j = " + j);
		}
	}

	/**
	 * To show j
	 */
	public static void show() {
		System.out.println("Name : " + Thread.currentThread().getName()
				+ " : j = " + j);
	}
}