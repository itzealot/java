package com.zt.design.behavior.iterator;

/**
 * 实现了获取迭代器的接口.<br />
 * 使用内部类来实现 Iterator 接口，以保持当前迭代的位置.<br />
 * 
 * @author zengtao
 *
 */
public class NameRepository implements Container<String> {

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

		public boolean hasNext() {
			if (index < names.length) {
				return true;
			}
			return false;
		}

		public String next() {
			if (this.hasNext()) {
				return names[index++];
			}
			return null;
		}
	}
}