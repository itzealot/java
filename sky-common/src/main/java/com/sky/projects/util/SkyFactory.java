package com.sky.projects.util;

/**
 * Factory to create Object
 * 
 * @author zealot
 *
 * @param <T>
 */
public interface SkyFactory<T> {

	/**
	 * 创建实例
	 * 
	 * @return
	 */
	T newInstance();

}