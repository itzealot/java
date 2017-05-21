package com.sky.projects.design.behavior.iterator.impl;

import com.sky.projects.design.behavior.iterator.Iterable;
import com.sky.projects.design.behavior.iterator.Iterator;

/**
 * 实现了获取迭代器的接口.<br />
 * 使用内部类来实现 Iterator 接口，以保持当前迭代的位置.<br />
 * 
 * @author zealot
 */
public class NameRepository implements Iterable<String> {

	// 需要迭代输出的String 数组
	public String names[] = { "Robert", "John", "Julie", "Lora" };

	public Iterator<String> getIterator() {
		return new NameIterator();
	}

	/**
	 * 使用内部类来实现 Iterator 接口，以保持当前迭代的位置
	 */
	private class NameIterator implements Iterator<String> {
		// 当前迭代索引
		int index = 0;

		@Override
		public boolean hasNext() {
			return index < names.length;
		}

		@Override
		public String next() {
			return this.hasNext() ? names[index++] : null;
		}
	}
}