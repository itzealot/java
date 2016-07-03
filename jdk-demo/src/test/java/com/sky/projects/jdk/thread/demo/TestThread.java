package com.sky.projects.jdk.thread.demo;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import com.sky.projects.jdk.thread.Threads;

/**
 * 不能改动此 TestThread 类
 * 
 * @author zengtao
 */
public class TestThread extends Thread {
	private TestDo testDo;
	private String key;
	private String value;

	public TestThread(String key, String key2, String value) {
		super();
		testDo = TestDo.getInstance();

		/**
		 * this.key为key与key2叠加，不为同一个对象，使用 synchronized 需要同一个对象才能锁住同步资源
		 * 
		 * a = "1" + "";// 常量，编译器会自动优化为a = "1";
		 * 
		 * b = "1" + "";// 常量，编译器会自动优化为b = "1";
		 * 
		 * 所以 a与b是同一个对象.
		 */
		this.key = key + key2;
		this.value = value;
	}

	@Override
	public void run() {
		testDo.doSome(key, value);
	}

	public static void main(String[] args) {
		/*
		 * 当每个线程中指定的key相等时，这些相等的key的线程每隔一秒输出(要用互斥)
		 * 
		 * 如果 key不同，则并行执行(则相互间不互斥)
		 */
		TestThread a = new TestThread("1", "", "1");
		TestThread b = new TestThread("1", "", "2");
		TestThread c = new TestThread("3", "", "3");
		TestThread d = new TestThread("4", "", "4");

		System.out.println("begin : " + (System.currentTimeMillis() / 1000));

		/**
		 * Origin Result: <br />
		 * begin : 1437363373 <br />
		 * 1 : 2 : 1437363374 <br />
		 * 1 : 1 : 1437363374 <br />
		 * 3 : 3 : 1437363374 <br />
		 * 4 : 4 : 1437363374 <br />
		 */

		/**
		 * Change result: <br />
		 * begin : 1437363373 <br />
		 * 1 : 2 : 1437363374 <br />
		 * 3 : 3 : 1437363374 <br />
		 * 4 : 4 : 1437363374 <br />
		 * 1 : 1 : 1437363375 <br />
		 */
		a.start();
		b.start();
		c.start();
		d.start();
	}
}

class TestDo {
	private static TestDo instance = new TestDo();

	public static TestDo getInstance() {
		return instance;
	}

	// 存储已经出现过 Key 的线程安全的ArrayList
	private CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<Object>();

	public void doSome(Object key, String value) {
		// 指向新的key，充当相应的锁
		Object o = key;

		// keys中不包含新增的key
		if (!keys.contains(o)) {
			// 不存在，则添加
			keys.add(o);
		} else { // 存在相应的 key,则指向旧的key，保证不会接连执行相同的 key 与 value
			for (Iterator<Object> iterator = keys.iterator(); iterator.hasNext();) {
				Object oo = iterator.next();

				if (oo.equals(o)) {
					// 指向原来的key
					o = oo;
				}
			}
		}

		// 锁相同，则会互斥
		synchronized (o) {// 大括号内的是需要局部同步的代码，不能改动
			Threads.sleep(1000);

			System.out.println(key + " : " + value + " : " + (System.currentTimeMillis() / 1000));
		}
	}
}