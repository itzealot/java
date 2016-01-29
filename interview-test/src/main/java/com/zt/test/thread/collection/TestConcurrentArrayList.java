package com.zt.test.thread.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 使用CopyOnWriteArrayList 实现在迭代的时候删除数据，实现多线程并发的ArrayList
 * 
 * @author zengtao
 *
 */
public class TestConcurrentArrayList {
	public static void main(String[] args) {

		// 1. 创建线程安全的 CopyOnWriteArrayList
		Collection<Integer> integers = new CopyOnWriteArrayList<Integer>();

		// 2. To add integers
		integers.add(1);
		integers.add(3);
		integers.add(2);
		integers.add(4);

		// 3. To get Iterator by Collection object
		Iterator<Integer> iterator = integers.iterator();

		// 4.迭代的时候可以删除
		while (iterator.hasNext()) {
			Integer integer = iterator.next();

			if (integer.equals(3)) {
				integers.remove(integer);
			} else {
				System.out.println(integer);
			}
		}
	}
}
