package com.zt.test.zookeeper.service;

import java.util.Collection;

/**
 * 目录接口
 * 
 * @author zt
 *
 * @param <T>
 */
public interface ZkDirService<T> {

	/**
	 * 获取父节点数据
	 * 
	 * @param t
	 * @param paths
	 * @return
	 */
	public T getParent(T t, String... paths);

	/**
	 * 获取所有子节点数据
	 * 
	 * @param t
	 * @param paths
	 * @return
	 */
	public Collection<T> getChildren(T t, String... paths);
}
