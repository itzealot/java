package com.sky.projects.jdk.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全的 ArrayList，CopyOnWriteArrayList 迭代的时候可执行删除数据
 * 
 * @author zealot
 *
 */
public class CopyOnWriteArrayListTest {

	public static void main(String[] args) {
		// 1. 创建线程安全的 CopyOnWriteArrayList
		Collection<Integer> integers = new CopyOnWriteArrayList<Integer>();

		// 2. 添加元素
		integers.add(1);
		integers.add(3);
		integers.add(2);
		integers.add(4);

		// 3. 获取迭代器
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
