package com.sky.projects.jdk.syn;

import com.sky.projects.jdk.thread.Threads;

public class ThreadTest {

	public static void main(String[] args) {
		new Thread(new SynThread1(new SynResource())).start();
		new Thread(new SynThread2(new SynResource())).start();

		new Thread(new SynStaticThread1()).start();
		new Thread(new SynStaticThread2()).start();
	}

	static class SynThread1 implements Runnable {
		private SynResource resource;

		public SynThread1(SynResource resource) {
			this.resource = resource;
		}

		@Override
		public void run() {
			while (true) {
				resource.add1();
			}
		}
	}

	static class SynThread2 implements Runnable {
		private SynResource resource;

		public SynThread2(SynResource resource) {
			this.resource = resource;
		}

		@Override
		public void run() {
			while (true) {
				resource.add2();
			}
		}
	}

	/**
	 * synchronized 关键字作用在实例方法上，锁为 this 对象
	 */
	static class SynResource {
		private int value = 0;

		public synchronized void add1() {
			System.out.println("ThreadName: " + Thread.currentThread().getName() + ", value=" + value);
			value++;
			Threads.sleep(1000);
		}

		public void add2() {
			synchronized (this) {
				System.out.println("ThreadName: " + Thread.currentThread().getName() + ", value=" + value);
				value += 2;
				Threads.sleep(1000);
			}
		}
	}

	/**
	 * 1. synchronized 关键字作用在静态方法上锁为类对应的 Class 对象
	 * 
	 * 2. 一个类中的多个静态同步方法被多线程调用，一次只能调用执行该类中的一个方法
	 */
	static class SynStaticThread1 implements Runnable {
		@Override
		public void run() {
			while (true) {
				SynStaticResource.add1();
			}
		}
	}

	static class SynStaticThread2 implements Runnable {
		@Override
		public void run() {
			while (true) {
				SynStaticResource.add2();
			}
		}
	}

	static class SynStaticThread3 implements Runnable {
		@Override
		public void run() {
			while (true) {
				SynStaticResource.add3();
			}
		}
	}

	/**
	 * 测试多个静态同步方法
	 */
	static class SynStaticResource {
		private static int value = 0;

		public static synchronized void add1() {
			System.out.println("ThreadName: " + Thread.currentThread().getName() + ", static value=" + value);
			value++;
			Threads.sleep(1000);
		}

		public synchronized static void add2() {
			System.out.println("ThreadName: " + Thread.currentThread().getName() + ", static value=" + value);
			value += 2;
			Threads.sleep(1000);
		}

		/**
		 * 等效方法，锁为 Class 对象
		 */
		public static void add3() {
			synchronized (SynStaticResource.class) {
				System.out.println("ThreadName: " + Thread.currentThread().getName() + ", static value=" + value);
				value += 3;
				Threads.sleep(1000);
			}
		}
	}
}
