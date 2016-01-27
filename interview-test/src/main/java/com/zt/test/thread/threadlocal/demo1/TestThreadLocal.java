package com.zt.test.thread.threadlocal.demo1;

import java.util.Random;

public class TestThreadLocal {

	// 放置多个变量，封装在一个对象中
	private static ThreadLocal<MyThread> threadLocal = new ThreadLocal<MyThread>();

	/**
	 * To get the bind thread
	 * 
	 * @return
	 */
	public MyThread get() {
		return threadLocal.get();
	}

	/**
	 * 向ThreadLocal<MyThread>中设置需要绑定的值.<br />
	 * 都以当前线程相关值来设定
	 */
	public void set(MyThread thread) {
		threadLocal.set(thread);
	}

	public static void main(String[] args) {

		// 创建两个线程
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				public void run() {

					// 随机产生一个整数
					int data = new Random().nextInt();
					String data2 = new Boolean(new Random().nextBoolean())
							.toString();

					MyThread myThread = new MyThread(data, data2);

					// 输出
					System.out.println(Thread.currentThread().getName()
							+ " has put data : " + myThread);

					// 向 ThreadLocal 放数据
					threadLocal.set(myThread);

					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	/**
	 * inner class
	 * 
	 * @author zengtao
	 */
	static class A {
		public void get() {
			MyThread data = threadLocal.get();
			System.out.println("A from " + Thread.currentThread().getName()
					+ " get data : " + data);
		}
	}

	/**
	 * inner class
	 * 
	 * @author zengtao
	 */
	static class B {
		public void get() {
			MyThread data = threadLocal.get();
			System.out.println("B from " + Thread.currentThread().getName()
					+ " get data : " + data);
		}
	}
}
