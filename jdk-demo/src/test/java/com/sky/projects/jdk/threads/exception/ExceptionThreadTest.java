package com.sky.projects.jdk.threads.exception;

import com.sky.projects.jdk.thread.exception.ExceptionThread;

public class ExceptionThreadTest {

	public static void main(String[] args) {
		System.out.println("==> Main thread running...");

		// 抛出异常的线程
		Thread thread = new Thread(new ExceptionThread());

		/*
		 * Thread.setDefaultUncaughtExceptionHandler() 方法设置默认处理程序，
		 * 当一个线程突然终止由于未捕获到异常出现时调用该程序，并且没有其他的处理程序已经为该线程定义
		 */
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("# Thread: " + t);
				System.out.println("# Thread exception message: " + e.getMessage());
			}
		});

		thread.start();
		
		// 主线程运行结束
		System.out.println("==> Main thread end...");
	}
}
