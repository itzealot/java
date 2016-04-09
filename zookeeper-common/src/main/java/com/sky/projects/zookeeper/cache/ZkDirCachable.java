package com.sky.projects.zookeeper.cache;

import java.util.Collection;

/**
 * 有关获取父目录和子目录的缓存接口
 * 
 * @author zt
 *
 */
public interface ZkDirCachable<T> {

	/**
	 * 获取父目录
	 * 
	 * @param t
	 * @return
	 */
	public T getParent(T t);

	/**
	 * 获取所有的子目录
	 * 
	 * @param t
	 * @return
	 */
	public Collection<T> getChildren(T t);
}
