package com.sky.projects.design.parser;

/**
 * 解析器接口
 * 
 * @author zealot
 */
public interface Parser<T> {

	/**
	 * parse
	 * 
	 * @param messages
	 * @return
	 */
	T parse(T[] messages);

	/**
	 * join
	 * 
	 * @param objs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	T join(T... objs);

	/**
	 * split
	 * 
	 * @param t
	 * @return
	 */
	T[] split(T message);
}
