package com.zt.test.thread.threadlocal.demo2;

import java.util.Random;

public class ThreadLocalDemo {
	// 放置一个变量
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				public void run() {

					// 随机产生一个整数
					int data = new Random().nextInt();

					// 输出
					System.out.println(Thread.currentThread().getName()
							+ " has put data : " + data);

					// 向 ThreadLocal 放数据
					local.set(data);

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
			int data = local.get();
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
			int data = local.get();
			System.out.println("B from " + Thread.currentThread().getName()
					+ " get data : " + data);
		}
	}
}
