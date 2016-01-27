package com.zt.test.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreeConditionCommunication {
	public static void main(String[] args) {

		final ConditionCommunication communication = new ConditionCommunication();
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					communication.method1(10);
				}
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				while (true) {
					communication.method2(20);
				}
			}
		}).start();

		new Thread(new Runnable() {

			public void run() {
				while (true) {
					communication.method3(50);
				}
			}
		}).start();
	}

	/**
	 * 使用Lock，Condition， flag来实现进程间的间隔访问通信.<br />
	 * 三个线程间间隔访问通信
	 * 
	 * @author zengtao
	 *
	 */
	static class ConditionCommunication {
		// 新建所对象
		private Lock lock = new ReentrantLock();

		/**
		 * 实现间隔访问的标志位
		 */
		private int flag = 1;

		/**
		 * 根据所对象创建3个Condition 对象.<br />
		 * condition1 用于线程1与线程2通信.<br />
		 * condition2 用于线程2与线程3通信.<br />
		 * condition3 用于线程3与线程1通信.<br />
		 */
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();

		/**
		 * 执行10遍打印
		 * 
		 * @param i
		 */
		public void method1(int i) {
			// 上锁
			lock.lock();

			try {

				// 不为1等待
				while (flag != 1) {
					try {
						// 阻塞；类似于wait 方法
						condition1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 相应处理方法
				for (int j = 1; j <= 10; j++) {
					System.out.println("method 1 : i = " + i + ", j = " + j);
				}

				// 修改标志位
				flag = 2;
				// 发信号，唤醒；类似于notify, notifyAll 方法
				condition2.signal();
			} finally {
				// 解锁
				lock.unlock();
			}
		}

		/**
		 * 执行20遍打印
		 * 
		 * @param i
		 */
		public void method2(int i) {
			// 上锁
			lock.lock();

			try {
				// 不为2等待
				while (flag != 2) {
					try {
						// 阻塞；类似于wait 方法
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 相应处理方法
				for (int j = 1; j <= 20; j++) {
					System.out.println("method 2 : i = " + i + ", j = " + j);
				}

				// 修改标志位
				flag = 3;

				// 发信号，唤醒；类似于notify, notifyAll 方法
				condition3.signal();
			} finally {
				// 解锁
				lock.unlock();
			}
		}

		/**
		 * 执行50遍打印
		 * 
		 * @param i
		 */
		public void method3(int i) {
			// 上锁
			lock.lock();

			try {
				// 不为3等待
				while (flag != 3) {
					try {
						// 阻塞；类似于wait 方法
						condition3.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// 相应处理方法
				for (int j = 1; j <= 50; j++) {
					System.out.println("method 3 : i = " + i + ", j = " + j);
				}

				// 修改标志位
				flag = 1;

				// 发信号，唤醒；类似于notify, notifyAll 方法
				condition1.signal();
			} finally {
				// 解锁
				lock.unlock();
			}
		}
	}
}
