package com.zt.test.thread;

/**
 * 多线程之间的通信.<br />
 * 线程1执行10次后，线程2执行50次;线程1执行10次后，线程2执行50次...<br />
 * 
 * @author zengtao
 *
 */
public class TraditionalThreadCommunication {

	public static void main(String[] args) {
		new ThreadManager().execute();
	}
}

/**
 * ThreadManager
 * 
 * @author zengtao
 *
 */
class ThreadManager {

	private static Object lock = new Object();
	private static boolean flag = false;
	private Thread thread10;
	private Thread thread50;

	public void execute() {
		thread10 = new Thread() {
			@Override
			public void run() {
				while (true) {
					// 锁
					synchronized (lock) {

						// flag为true，执行a
						if (flag) {
							Task.excute("Thread-1", 10);

							// 修改位false
							flag = false;

							// 唤醒等待的线程；即唤醒b
							lock.notify();
							try {
								// 自己进入等待；即a等待
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		};

		thread50 = new Thread() {
			@Override
			public void run() {
				while (true) {
					// 锁
					synchronized (lock) {

						// flag为true，执行a
						if (!flag) {
							Task.excute("Thread-2", 10);

							// 修改位false
							flag = true;

							// 唤醒等待的线程；即唤醒b
							lock.notify();
							try {
								// 自己进入等待；即a等待
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		};

		thread10.start();
		thread50.start();
	}
}

/**
 * 任务类 Task
 * 
 * @author zengtao
 *
 */
class Task {

	/**
	 * 执行任务
	 * 
	 * @param name
	 * @param counts
	 */
	public static void excute(String name, int counts) {
		for (int i = 1; i <= counts; i++) {
			System.out.println("Thread name is : " + name + ", " + i);
		}
		System.out.println();
	}
}
