package com.sky.projects.jdk.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sky.projects.jdk.thread.Threads;

/**
 * 使用 Lock的实现类ReentrantLock实现同步互斥访问，前提是必须使用同一个Lock对象
 * 
 * @author zealot
 *
 */
public class TestLock {

	/** 用于控制互斥访问的所对象 */
	private final Lock lock = new ReentrantLock();

	/**
	 * 使用Lock 来互斥访问，lock.unlock方法解锁 需放置在finally 块中.<br />
	 * 
	 * @param name
	 */
	public void outputWithSyn(String name) {
		try {
			lock.lock(); // 上锁

			output(name);
		} finally {
			// 解锁，需放入到 finally 中
			lock.unlock();
		}
	}

	public void output(String name) {
		for (int i = 0; i < 10; i++) {
			System.out.print(name);
			Threads.sleep(i);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TestLock testLock = new TestLock();
		// print(testLock);
		synPrint(testLock);
	}

	/**
	 * 使用Lock进行同步打印
	 * 
	 * @since 1.8
	 * @param testLock
	 */
	public static void synPrint(TestLock testLock) {
		new Thread(() -> {
			Random random = new Random();

			while (true) {
				testLock.outputWithSyn("222");
				Threads.sleep(random.nextInt(500) + 200);
			}
		}).start();

		new Thread(() -> {
			Random random = new Random();

			while (true) {
				testLock.outputWithSyn("111");
				Threads.sleep(random.nextInt(500) + 400);
			}
		}).start();
	}

	/**
	 * 非同步打印信息
	 * 
	 * @since 1.8
	 * @param testLock
	 */
	public static void print(TestLock testLock) {
		new Thread(() -> {
			Random random = new Random();

			while (true) {
				testLock.output("222");
				Threads.sleep(random.nextInt(500) + 500);
			}
		}).start();

		new Thread(() -> {
			Random random = new Random();

			while (true) {
				testLock.output("111");
				Threads.sleep(random.nextInt(500) + 500);
			}
		}).start();
	}
}
