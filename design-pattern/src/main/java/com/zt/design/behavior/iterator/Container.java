package com.zt.design.behavior.iterator;

/**
 * interface Container 封装了含有获取迭代器的方法.<br />
 * 
 * @author zengtao
 *
 */
public interface Container<T> {
	/**
	 * 获取迭代器
	 * 
	 * @return
	 */
	public Iterator<T> getIterator();
}