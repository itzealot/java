package com.sky.projects.jdk.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试java.util.concurrent.locks 包下的Lock.<br />
 * 来那个线程执行的代码片段需要实现同步互斥的效果，必须使用同一个Lock对象.<br />
 * 
 * @author zealot
 *
 */
public class TestLock {
	/**
	 * 用于控制互斥访问的所对象
	 */
	Lock lock = new ReentrantLock();

	/**
	 * 使用Lock 来互斥访问，输出信息.<br />
	 * lock.unlock()方法 解锁 需放置在finally 块中.<br />
	 * 
	 * @param name
	 */
	public void output(String name) {

		// 1. 当前线程上锁
		lock.lock();

		try {
			// 2. 执行方法
			for (int i = 0; i < 3; i++) {
				System.out.print(name);
			}
			System.out.println();
		} finally {

			/**
			 * 3. 当前线程解锁，为防止在执行2时出现异常，使用finally来解锁.<br />
			 * 无论何时都会调用lock.unlock()方法 解锁
			 */
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					new TestLock().output("zhangsan");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				while (true) {
					new TestLock().output("LISI");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
