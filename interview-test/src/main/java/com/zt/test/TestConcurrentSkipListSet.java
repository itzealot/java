package com.zt.test;

import java.util.concurrent.ConcurrentSkipListSet;

public class TestConcurrentSkipListSet {

	public static void main(String[] args) {

		// 保证数据有序，线程安全
		ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<Integer>();

		Runnable runnable1 = new StringAppendThread(set);

		Runnable runnable2 = new StringAppendThread(set);

		Thread t1 = new Thread(runnable1);

		Thread t2 = new Thread(runnable2);

		t1.start();
		t2.start();
	}
}
