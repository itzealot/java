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
	 * @param paths
	 */
	public void add(T t, String... paths);

	/**
	 * 更新数据
	 * 
	 * @param t
	 * @param paths
	 */
	public void update(T t, String... paths);

	/**
	 * 删除数据
	 * 
	 * @param t
	 * @param paths
	 */
	public void delete(T t, String... paths);

	/**
	 * 根据路径获取数据
	 * 
	 * @param path
	 * @return
	 */
	public T get(String path);

	/**
	 * 获取数据
	 * 
	 * @param paths
	 * @return
	 */
	public T get(String... paths);

	/**
	 * 查询所有数据
	 * 
	 * @param paths
	 * @return
	 */
	Collection<T> findAll(String... paths);

}
