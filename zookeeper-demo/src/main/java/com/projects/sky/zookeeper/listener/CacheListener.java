package com.projects.sky.zookeeper.listener;

import java.util.Map;

/**
 * CacheListener<T>
 * 
 * @author zt
 * @date 2016年1月5日
 */
public interface CacheListener<T> {
	/**
	 * 远程删除
	 * 
	 * @param t
	 */
	void remoteDelete(T t);

	/**
	 * 远程更新
	 * 
	 * @param t
	 */
	void remoteUpdate(T t);

	/**
	 * 远程添加
	 * 
	 * @param t
	 */
	void remoteAdd(T t);

	/**
	 * 初始化
	 * 
	 * @param t
	 */
	void init(Map<String, T> cache);

}
