package com.sky.projects.jdk.thread;

/**
 * 线程A,B间的通信，形成互斥访问
 * 
 * @author zealot
 *
 */
public class TwoThread {
	// 锁为静态成员变量，持有的是相同的对象
	private static final Object LOCK = new Object();

	// 指定由谁运行
	private static boolean runningA = false;

	static class RunnerA implements Runnable {
		@Override
		public void run() {
			while (true) {
				if (runningA) {
					synchronized (LOCK) {// 同步代码块，互斥访问
						if (runningA) {
							System.out.println("Thread-1");
							Threads.sleep(1000);

							runningA = false;
							LOCK.notify(); // 唤醒等待的线程

							try {
								LOCK.wait();
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
						}
					}
				}
			}
		}
	}

	static class RunnerB implements Runnable {
		@Override
		public void run() {
			while (true) {
				if (!runningA) {
					synchronized (LOCK) {
						if (!runningA) {
							System.out.println("Thread-2");
							Threads.sleep(1000);

							runningA = true;
							LOCK.notify(); // 唤醒等待线程

							try {
								LOCK.wait();
							} catch (InterruptedException e) {
								Thread.currentThread().interrupt();
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new RunnerA(), "RunnerA").start();
		new Thread(new RunnerB(), "RunnerB").start();
	}
}