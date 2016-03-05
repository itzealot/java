package com.zt.design.behavior.iterator;

/**
 * 获取迭代器的接口
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