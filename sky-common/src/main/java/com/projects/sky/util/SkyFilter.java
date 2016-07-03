package com.projects.sky.util;

public interface SkyFilter<T> {
	/**
	 * 是否接收元素 t
	 * 
	 * @param t
	 * @return
	 */
	public boolean accept(T t);
}
