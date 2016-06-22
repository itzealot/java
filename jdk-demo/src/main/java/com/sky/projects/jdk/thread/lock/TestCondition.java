package com.sky.projects.jdk.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition 类进行线程间的通信，类似于wait, notify, notifyAll方法.<br />
 * Condition 即 通信的条件.<br />
 * 
 * @author zengtao
 *
 */
public class TestCondition {

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
					communication.method2(50);
				}
			}
		}).start();
	}
}

/**
 * 使用Lock，Condition， flag 来实现进程间的间隔访问通信
 * 
 * @author zengtao
 *
 */
class ConditionCommunication {
	// 新建所对象
	private Lock lock = new ReentrantLock();

	// 实现间隔访问的标志位
	private boolean flag = true;

	// 根据所对象创建Condition 对象
	private Condition condition = lock.newCondition();

	/**
	 * 执行10遍打印
	 * 
	 * @param i
	 */
	public void method1(int i) {
		// 上锁
		lock.lock();

		try {

			// 为false等待；否则执行
			if (!flag) {
				try {
					// 阻塞；类似于wait 方法
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 相应处理方法
			System.out.println("method1....................................");
			Thread.sleep(new Random().nextInt(100));
			// 修改标志位为false
			flag = false;
			// 发信号，唤醒；类似于notify, notifyAll 方法
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	public void method2(int i) {
		// 上锁
		lock.lock();

		try {
			// 为真等待；否则执行下面的方法
			if (flag) {
				try {
					// 阻塞；类似于wait 方法
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 相应处理方法
			System.out.println("method2....................................");
			Thread.sleep(new Random().nextInt(100));
			// 修改标志位为true
			flag = true;

			// 发信号，唤醒；类似于notify, notifyAll 方法
			condition.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 解锁
			lock.unlock();
		}
	}
}