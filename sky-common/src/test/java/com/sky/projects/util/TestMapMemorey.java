package com.sky.projects.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMapMemorey {

	public static void main(String[] args) {
		Integer a = 1;
		long start = 0;
		long end = 0;
		// 先垃圾回收
		System.gc();
		start = Runtime.getRuntime().freeMemory();

		Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
		for (int i = 0; i < 2000000; i++) {
			map.put(i, a);
		}

		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 快要计算的时,再清理一次
		System.gc();
		end = Runtime.getRuntime().freeMemory();
		System.out.println("一个HashMap对象占内存:" + (end - start));
	}

}
