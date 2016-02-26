package com.zt.test.zookeeper.service;

import java.util.Collection;

/**
 * 基于 Zookeeper 的crud接口
 * 
 * @author zt
 *
 * @param <T>
 */
public interface ZkService<T> {

	/**
	 * 添加数据
	 * 
	 * @param t
	 * @param path
	 */
	public void add(T t, String path);

	/**
	 * 更新数据
	 * 
	 * @param t
	 * @param path
	 */
	public void update(T t, String path);

	/**
	 * 删除数据
	 * 
	 * @param t
	 * @param path
	 */
	public void delete(T t, String path);

	/**
	 * 根据路径获取数据
	 * 
	 * @param path
	 * @return
	 */
	public T get(String path);

	/**
	 * 查询所有数据
	 * 
	 * @param path
	 * @return
	 */
	Collection<T> findAll(String path);

}
