package com.sky.projects.jdk.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 1. 实现读与写的互斥访问，读一读的同步访问.<br />
 * 2. 使用Lock 不可以，不能控制读进程与写进程互斥访问.<br />
 * 3. 应该使用ReadWriteLock lock = new ReentrantReadWriteLock()来实现读与写互斥访问.<br />
 * 4. 读锁 lock.readLock().lock(); 解读锁 ： lock.readLock().unlock();.<br />
 * 4. 写锁 lock.writeLock().lock(); 解写锁 ： lock.writeLock().unlock();.<br />
 * 
 * @author zealot
 *
 */
public class TestReadAndWriteLock {

	public static void main(String[] args) {

		final BuffData buff = new BuffData();

		// 循环三次，依次创建读进程与写进程
		for (int i = 0; i < 3; i++) {

			// 创建读进程
			new Thread(new Runnable() {
				public void run() {
					while (true) {

						// 读取data
						buff.read();
					}
				}
			}).start();

			// 创建写进程
			new Thread(new Runnable() {
				public void run() {
					while (true) {

						// wirte data
						buff.write(new Random().nextInt());
					}
				}
			}).start();
		}
	}
}

/**
 * 实现读与写的互斥访问，读与读之间同步访问.<br />
 * 读与写应该在同一个类中.<br />
 * 
 * @author zealot
 *
 */
class BuffData {
	// 缓冲数据
	private Integer data = null;

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	/**
	 * To read data
	 */
	public void read() {
		// 1.得到读锁且上锁
		lock.readLock().lock();

		try {
			// 2. 读数据代码
			System.out.println(Thread.currentThread().getName()
					+ " : start read data");
			Thread.sleep((long) (1000 * Math.random()));

			System.out.println(Thread.currentThread().getName()
					+ " : finish read data = " + data);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 3. 得到读锁且解锁
			lock.readLock().unlock();
		}
	}

	/**
	 * To write data
	 */
	public void write(Integer data) {
		// 1.得到写锁且上锁
		lock.writeLock().lock();

		try {
			// 2. 写数据代码
			System.out.println(Thread.currentThread().getName()
					+ " : start write data");
			Thread.sleep((long) (1000 * Math.random()));

			this.data = data;

			System.out.println(Thread.currentThread().getName()
					+ " : finish write data = " + this.data);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 3. 得写读锁且解锁
			lock.writeLock().unlock();
		}
	}
}
