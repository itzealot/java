package com.sky.projects.jdk.thread.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestPrintLog {

	public static void main(String[] args) {
		// testOrigin();
		testChange();
	}

	/**
	 * 使用4个线程来打印日志，一共需要4秒
	 */
	public static void testChange() {
		// 创建容量为16个任务的阻塞队列；也可以为1
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);

		System.out.println("begin : " + System.currentTimeMillis() / 1000);

		/**
		 * 模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完.<br />
		 * 修改程序代码，开四个线程让这16个对象在四秒钟打完日志.<br />
		 * 即每个线程打4个日志，需要4秒；4个线程并行一共需要4秒.<br />
		 */
		for (int i = 0; i < 16; i++) {// 改行代码不能改
			final String log = "" + (i + 1);// 改行代码不能改

			// 添加到阻塞队列
			try {
				queue.put(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 开四个线程进行打印日志
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							// 取日志信息并打印
							String log = queue.take();
							TestPrintLog.printLog(log);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public static void testOrigin() {
		System.out.println("begin : " + System.currentTimeMillis() / 1000);

		/**
		 * 模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完.<br />
		 * 修改程序代码，开四个线程让这16个对象在四秒钟打完日志.<br />
		 * 即每个线程打4个日志，需要4秒；4个线程并行一共需要4秒.<br />
		 */
		for (int i = 0; i < 16; i++) {// 改行代码不能改
			final String log = "" + (i + 1);// 改行代码不能改
			TestPrintLog.printLog(log);
		}

		System.out.println("end : " + System.currentTimeMillis() / 1000);
	}

	/**
	 * 模拟打印日志的方法.<br />
	 * 更改时不能改动.<br />
	 * 
	 * @param log
	 */
	public static void printLog(String log) {// 改方法不能改
		System.out.println(log + " : " + System.currentTimeMillis() / 1000);

		try {
			// 休息1秒
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
