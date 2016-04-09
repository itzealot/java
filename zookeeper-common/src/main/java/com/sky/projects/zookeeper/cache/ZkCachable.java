package com.sky.projects.zookeeper.cache;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存公共接口
 * 
 * @author zt
 *
 * @param <K>
 * @param <V>
 */
public interface ZkCachable<K, V> {
	/**
	 * 根据 Key 删除缓存，返回被删除的对象
	 * 
	 * @param key
	 * @return
	 */
	V delete(K key);

	/**
	 * 更新缓存，返回旧的对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	V update(K key, V value);

	/**
	 * 向缓存中添加对象
	 * 
	 * @param key
	 * @param value
	 */
	void add(K key, V value);

	/**
	 * 根据 Key 从缓存中获取对象
	 * 
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * 获取所有缓存对象
	 * 
	 * @return
	 */
	Collection<V> getAll();

	/**
	 * 使用线程安全的 Map 初始化缓存
	 * 
	 * @param cache
	 */
	void init(ConcurrentHashMap<K, V> cache);

}