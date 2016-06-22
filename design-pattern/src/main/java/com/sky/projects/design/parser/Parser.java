package com.sky.projects.design.parser;

public interface Parser<T> {
	/**
	 * parse
	 * 
	 * @param messages
	 * @return
	 */
	public T parse(T[] messages);

	/**
	 * join
	 * 
	 * @param objs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T join(T... objs);

	/**
	 * split
	 * 
	 * @param t
	 * @return
	 */
	public T[] split(T message);
}
