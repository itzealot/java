package com.sky.projects.design.behavior.iterator;

/**
 * 迭代器接口
 * 
 * @author zealot
 */
public interface Iterator<T> {

	/**
	 * 是否含有后继，若有，返回true；否则返回false
	 * 
	 * @return
	 */
	public boolean hasNext();

	/**
	 * 获取后继节点
	 * 
	 * @return
	 */
	public T next();
}
