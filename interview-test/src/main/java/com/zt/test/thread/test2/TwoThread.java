package com.zt.test.thread.test2;

/**
 * 线程A,B间的通信，形成互斥访问
 * 
 * @author zengtao
 *
 */
public class TwoThread {

	// 锁为静态成员变量，持有的是相同的对象
	private static Object lock = new Object();

	// 指定由谁运行
	private static boolean flag = false;

	public static void main(String[] args) {
		// 创建线程a
		Thread a = new Thread() {
			public void run() {
				while (true) {
					// 锁
					synchronized (lock) {

						// flag为true，执行a
						if (flag) {
							System.out.println("Thread-1");

							// 修改位false
							flag = false;

							// 唤醒等待的线程；即唤醒b
							lock.notify();
							try {
								// 自己进入等待；即a等待
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				}
			}
		};

		// 创建线程b
		Thread b = new Thread() {
			public void run() {
				while (true) {
					synchronized (lock) {
						// flag为false，执行b
						if (!flag) {
							System.out.println("Thread-2");
							// 修改flag 为 true
							flag = true;

							// 唤醒等待线程，即唤醒线程a
							lock.notify();
							try {
								// 自己等待；即b等待
								lock.wait();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
					}

				}
			}
		};

		// 启动线程
		a.start();
		b.start();
	}
}