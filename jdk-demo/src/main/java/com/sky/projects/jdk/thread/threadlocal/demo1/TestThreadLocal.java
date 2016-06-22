package com.sky.projects.jdk.thread.threadlocal.demo1;

import java.util.Random;

import com.sky.projects.jdk.thread.threadlocal.A;
import com.sky.projects.jdk.thread.threadlocal.B;

/**
 * 每个线程放入数据到ThreadLocal 中，取数据时只取出自己放的数据
 * 
 * @author zt
 */
public class TestThreadLocal {
	// 放置多个变量，封装在一个对象中
	private static ThreadLocal<MyThread> threadLocal = new ThreadLocal<MyThread>();

	public static void main(String[] args) {
		// 创建两个线程并启动
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					MyThread myThread = new MyThread(new Random().nextInt(),
							new Boolean(new Random().nextBoolean()).toString());

					System.out.println(Thread.currentThread().getName() + " has put data : " + myThread);

					threadLocal.set(myThread); // 向 ThreadLocal 放数据

					// 创建两个实例分别从 ThreadLocal 中获取数据
					new A<MyThread>(threadLocal).get();
					new B<MyThread>(threadLocal).get();
				}
			}).start();
		}

		new Thread() {
			@Override
			public void run() {
				System.out.println("run...");
			}
		}.start();
	}

	/**
	 * 向ThreadLocal<MyThread>中设置需要绑定的值,都以当前线程相关值来设定
	 */
	public void set(MyThread thread) {
		threadLocal.set(thread);
	}

	/**
	 * 从 ThreadLocal 中获取绑定的值
	 * 
	 * @return
	 */
	public MyThread get() {
		return threadLocal.get();
	}

}
