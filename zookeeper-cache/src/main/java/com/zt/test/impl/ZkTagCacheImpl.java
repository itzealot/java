package com.zt.test.impl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.zt.test.ZkCachable;
import com.zt.test.ZkTagCachable;
import com.zt.test.entity.TagCatalog;

/**
 * 服务标签缓存实现类
 * 
 * @author zt
 *
 */
public class ZkTagCacheImpl implements ZkTagCachable {

	private static ZkCachable<String, TagCatalog> tags = null;

	private static ZkTagCachable instance = null;

	private static Object lock = new Object();

	private ZkTagCacheImpl() {
	}

	/**
	 * 获取组织机构缓存的实例对象
	 * 
	 * @return
	 */
	public static ZkTagCachable getInstance() {
		if (instance == null) {
			synchronized (lock) {
				tags = new ZkCacheImpl<String, TagCatalog>();

				instance = new ZkTagCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public TagCatalog delete(String key) {
		return tags.delete(key);
	}

	@Override
	public TagCatalog update(String key, TagCatalog value) {
		return tags.update(key, value);
	}

	@Override
	public void add(String key, TagCatalog value) {
		tags.add(key, value);
	}

	@Override
	public TagCatalog get(String key) {
		return tags.get(key);
	}

	@Override
	public Collection<TagCatalog> getAll() {
		return tags.getAll();
	}

	@Override
	public void init(ConcurrentHashMap<String, TagCatalog> cache) {
		tags.init(cache);
	}

}
