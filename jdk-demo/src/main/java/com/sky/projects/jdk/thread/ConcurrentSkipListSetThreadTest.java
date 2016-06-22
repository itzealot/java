package com.sky.projects.jdk.thread;

import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentSkipListSetThreadTest {

	public static void main(String[] args) {
		// 保证数据有序，线程安全
		ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<Integer>();
		new Thread(new ConcurrentSkipListSetThread(set)).start();
		new Thread(new ConcurrentSkipListSetThread(set)).start();
	}
}
