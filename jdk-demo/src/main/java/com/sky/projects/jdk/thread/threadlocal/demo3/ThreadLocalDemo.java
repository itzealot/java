package com.sky.projects.jdk.thread.threadlocal.demo3;

import java.util.Random;

import com.sky.projects.jdk.thread.threadlocal.A;
import com.sky.projects.jdk.thread.threadlocal.B;

/**
 * 线程的状态，枚举常量 State. <br>
 * --BLOCKED : 受阻塞并且正在等待监视器锁的某一线程的线程状态. <br>
 * --NEW : 至今尚未启动的线程的状态. <br>
 * --RUNNABLE : 可运行线程的线程状态. <br>
 * --TERMINATED : 已终止线程的线程状态. <br>
 * --TIMED_WAITING : 具有指定等待时间的某一等待线程的线程状态. <br>
 * --WAITING : 某一等待线程的线程状态.<br>
 * 
 * @author zealot
 *
 */
public class ThreadLocalDemo {

	public static void main(String[] args) {
		Thread[] thread = new Thread[2];
		int length = thread.length;

		for (int i = 0; i < length; i++) {
			thread[i] = new Thread(new Runnable() {
				public void run() {
					// 随机产生一个整数
					int id = new Random().nextInt();
					String name = new Boolean(new Random().nextBoolean()).toString();

					MyThread.getInstance().setId(id);
					MyThread.getInstance().setName(name);

					System.out.println(Thread.currentThread().getName() + " has put id : " + id + ", name : " + name);

					new A<MyThread>(MyThread.local).get();
					new B<MyThread>(MyThread.local).get();
				}
			});

			thread[i].start();
		}

		int counts = 0;// 标志线程是否已经完成的计数统计
		boolean[] flag = new boolean[length];

		// 主线程持续监听创建的线程
		while (true) {
			for (int i = 0; i < length; i++) {
				if (!flag[i] && Thread.State.TERMINATED == thread[i].getState()) {
					MyThread.remove();
					System.out.println("thread[" + i + "] terminate");
					counts++;
					flag[i] = true;
				}
			}

			// 线程全部结束，则主线程监听退出
			if (counts == 2) {
				break;
			}
		}
	}

	/**
	 * 得到当前线程绑定的对象，单例实现，内部使用ThreadLocal<T> 实现.<br />
	 */
	static class MyThread {
		public static ThreadLocal<MyThread> local = new ThreadLocal<MyThread>();
		private static MyThread instance = null;
		private Integer id;
		private String name;

		private MyThread(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		private MyThread() {
			super();
		}

		/**
		 * 通过ThreadLocal的方式实现单例，ThreadLocal是与当前线程相关，不涉及多线程问题.
		 * 
		 * @return
		 */
		public static MyThread getInstance() {
			// 1. 从ThreadLocal中获取
			instance = local.get();

			// 2. 没获取到，则创建并添加到ThreadLocal中
			if (instance == null) {
				instance = new MyThread();

				// 3. 添加到ThreadLocal
				local.set(instance);
			}

			return instance;
		}

		/**
		 * To remove the ThreadLocal<T> instance
		 */
		public static void remove() {
			local.remove();
			instance = null;
			System.out.println("remove the ThreadLocal<T> instance");
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "MyThread [id=" + id + ", name=" + name + "]";
		}
	}
}
