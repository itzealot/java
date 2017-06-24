package com.sky.projects.jdk.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sky.projects.jdk.thread.Threads;

/**
 * 使用 Lock，Condition， flag 来实现线程间的间隔访问通信
 * 
 * @author zealot
 *
 */
public class ConditionCommunication {
	// 独占锁对象，用于控制访问
	private final Lock lock = new ReentrantLock();
	// 锁对象绑定的 Condition 对象
	private final Condition condition = lock.newCondition();
	private final Random random = new Random();

	// 实现间隔访问的标志位
	private boolean flag = true;

	/**
	 * flag 为false时等待，否则执行相关操作，并修改flag为false，并唤醒等待线程
	 * 
	 * @param counts
	 */
	public void doPrintWhenTrue(int counts) {
		try {
			lock.lock(); // 上锁

			// 为false等待；否则执行
			while (!flag) {
				try {
					// 阻塞；类似于 wait 方法
					condition.await();
				} catch (InterruptedException e) {
					Thread.currentThread();
				}
			}

			// 相应处理方法
			doPrint(counts);

			// 修改标志位为false
			flag = false;

			// 发信号，唤醒一个等待线程；类似于 notify
			condition.signal();
		} finally {
			lock.unlock(); // 解锁
		}
	}

	/**
	 * flag 为true时等待，否则执行相关操作，并修改flag为true，并唤醒等待线程
	 * 
	 * @param counts
	 */
	public void doPrintWhenFalse(int counts) {
		try {
			lock.lock(); // 上锁

			while (flag) { // 为真等待；否则执行下面的方法
				try {
					condition.await(); // 阻塞；类似于wait 方法
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}

			doPrint(counts);

			// 修改标志位为true
			flag = true;

			// 发信号，唤醒；类似于 Object.notify
			condition.signal();
		} finally {
			lock.unlock(); // 解锁
		}
	}

	private void doPrint(int counts) {
		int sum = 0;
		for (int i = 0; i < counts; i++) {
			sum++;
			Threads.sleep(100 + random.nextInt(100));
		}
		System.out.println("sum :" + sum);
	}
}