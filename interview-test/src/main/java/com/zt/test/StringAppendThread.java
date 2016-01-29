package com.zt.test;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class StringAppendThread implements Runnable {
	private ConcurrentSkipListSet<Integer> set;

	public ConcurrentSkipListSet<Integer> getSet() {
		return set;
	}

	public StringAppendThread(ConcurrentSkipListSet<Integer> set) {
		super();
		this.set = set;
	}

	@Override
	public void run() {
		while (true) {
			// 产生数字并添加到 ConcurrentSkipListSet 中
			set.add(new Random().nextInt(100));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			display();
		}
	}

	public void display() {
		System.out.println(set);
	}
}
