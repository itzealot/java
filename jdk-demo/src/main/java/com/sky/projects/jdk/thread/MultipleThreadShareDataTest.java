package com.sky.projects.jdk.thread;

/**
 * 使用实现的同一个Runnable接口的类实现共享数据
 * 
 * @author zengtao
 *
 */
public class MultipleThreadShareDataTest {

	public static void main(String[] args) {
		// 使用实现的同一个 Runnable 接口的类实现共享数据
		MultipleThreadShareData data = new MultipleThreadShareData();

		// 多线程执行时，如果涉及对共享内容的写操作，需要使用锁
		new Thread(data).start();
		new Thread(data).start();
	}
}