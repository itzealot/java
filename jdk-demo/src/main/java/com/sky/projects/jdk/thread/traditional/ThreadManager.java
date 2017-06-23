package com.sky.projects.jdk.thread.traditional;

/**
 * 线程管理
 * 
 * @author zealot
 */
public class ThreadManager {
	// 锁
	private static final Object LOCK = new Object();
	// 是否运行 thread10
	private static boolean flag = false;

	/**
	 * 线程管理类执行入口
	 */
	public void execute() {
		Thread thread10 = new Thread() {
			@Override
			public void run() {
				while (true) {
					synchronized (LOCK) {
						if (flag) {
							Task.excute("Thread-1", 10); // 执行任务

							flag = false;// 修改位false

							LOCK.notify();// 唤醒等待的线程；即唤醒 thread20

							try {
								LOCK.wait(); // 自己进入等待；即 thread10 等待
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};

		Thread thread20 = new Thread() {
			@Override
			public void run() {
				while (true) {
					synchronized (LOCK) {
						if (!flag) {
							// 执行任务
							Task.excute("Thread-2", 20);

							flag = true;
							LOCK.notify();// 唤醒等待的线程；即唤醒 thread10
							try {
								LOCK.wait(); // 自己进入等待；即 thread20 等待
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};

		// 启动线程
		thread10.start();
		thread20.start();
	}
}