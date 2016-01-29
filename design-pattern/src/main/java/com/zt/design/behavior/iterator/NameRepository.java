package com.zt.design.behavior.iterator;

/**
 * 1. class NameRepository implements Container.<br />
 * 2. 实现了获取迭代器的借口.<br />
 * 3. 使用内部类来实现Iterator接口，以保持当前迭代的位置.<br />
 * 
 * @author zengtao
 *
 */
public class NameRepository implements Container<String> {

	// 需要迭代输出的String 数组
	public String names[] = { "Robert", "John", "Julie", "Lora" };

	/**
	 * 获取内部类实现的迭代器对象
	 */
	public Iterator<String> getIterator() {
		return new NameIterator();
	}

	/**
	 * 使用内部类来实现Iterator接口，以保持当前迭代的位置.<br />
	 * 
	 * @author zengtao
	 *
	 */
	private class NameIterator implements Iterator<String> {
		// 当前迭代索引
		int index = 0;

		/**
		 * has next
		 */
		public boolean hasNext() {
			if (index < names.length) {
				return true;
			}
			return false;
		}

		/**
		 * 获取下一个要迭代的对象节点
		 */
		public String next() {
			if (this.hasNext()) {
				return names[index++];
			}
			return null;
		}
	}
}