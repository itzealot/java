package com.zt.test.impl;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zt.test.ZkCachable;

/**
 * 公共操作的缓存实现类
 * 
 * @author zt
 *
 * @param <K>
 * @param <V>
 */
public class ZkCacheImpl<K, V> implements ZkCachable<K, V> {

	private Map<K, V> cache;

	ZkCacheImpl() {
	}

	@Override
	public V delete(K key) {
		return cache.remove(key);
	}

	@Override
	public V update(K key, V value) {
		return cache.put(key, value);
	}

	@Override
	public void add(K key, V value) {
		cache.put(key, value);
	}

	@Override
	public V get(K key) {
		return cache.get(key);
	}

	@Override
	public Collection<V> getAll() {
		return cache.values();
	}

	@Override
	public void init(ConcurrentHashMap<K, V> cache) {
		this.cache = null;
		this.cache = cache;
	}

}
