package com.sky.projects.design.behavior.iterator;

/**
 * 获取迭代器的接口
 * 
 * @author zealot
 */
@FunctionalInterface
public interface Iterable<T> {

	/**
	 * 获取迭代器
	 * 
	 * @return
	 */
	Iterator<T> getIterator();
}