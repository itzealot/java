package com.apusic.sicp.util;

import java.util.Map;

import org.apache.commons.collections.map.LRUMap;

/**
 * 用户名->角色名列表Cache
 * 
 * @author zealot
 * 
 */
public class MemoryCache {

	// 过期长度,毫秒
	private final long expired;

	// 容量
	@SuppressWarnings("unused")
	private final int capacity;

	private final Map<String, CacheItem> items;

	@SuppressWarnings("unchecked")
	public MemoryCache(long expired, int capacity) {
		super();
		this.expired = expired;
		this.capacity = capacity;
		this.items = new LRUMap(capacity);
	}

	public synchronized void put(String key, Object data) {
		CacheItem item = new CacheItem(data);
		item.cacheTime = System.currentTimeMillis();
		items.put(key, item);
	}

	public synchronized Object get(String key) {
		CacheItem item = items.get(key);
		if (item != null && (item.cacheTime + expired < System.currentTimeMillis())) {
			items.remove(key);
			return null;
		}
		return item != null ? item.data : null;
	}

	public synchronized Object remove(String key) {
		return this.items.remove(key);
	}

	static class CacheItem {
		long cacheTime = System.currentTimeMillis();
		Object data = null;

		public CacheItem(Object data) {
			super();
			this.data = data;
		}

	}
}
