package com.sky.projects.jdk.thread.cache;

import java.util.Collection;
import java.util.Set;

/**
 * Cache
 * 
 * @author zealot
 *
 * @param <K>
 * @param <V>
 */
public interface Cache<K, V> {

	/**
	 * 根据指定的Key值获取Value，并返回旧的值
	 * 
	 * @param key
	 * @param value
	 * @return 存在则返回旧的值，否则返回null
	 */
	V put(K key, V value);

	/**
	 * 根据指定的Key值获取Value，不存在则设置
	 * 
	 * @param key
	 * @param value
	 */
	void putIfAbsent(K key, V value);

	/**
	 * 根据指定的Key获取Value
	 * 
	 * @param key
	 * @return 存在返回Value，否则返回null
	 */
	V get(K key);

	/**
	 * 获取所有的Key
	 * 
	 * @return
	 */
	Set<K> keys();

	/**
	 * 返回所有的Value
	 * 
	 * @return
	 */
	Collection<V> values();
}
