package com.zt.test.impl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.zt.test.ZkCachable;
import com.zt.test.ZkServiceCachable;
import com.zt.test.entity.Node;
import com.zt.test.entity.Service;

/**
 * 服务缓存实现类
 * 
 * @author zt
 *
 */
public class ZkServiceCacheImpl implements ZkServiceCachable {

	private static ZkCachable<String, Service> tags = null;

	private static ZkServiceCachable instance = null;

	private static Object lock = new Object();

	private ZkServiceCacheImpl() {
	}

	/**
	 * 获取组织机构缓存的实例对象
	 * 
	 * @return
	 */
	public static ZkServiceCachable getInstance() {
		if (instance == null) {
			synchronized (lock) {
				tags = new ZkCacheImpl<String, Service>();

				instance = new ZkServiceCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public Service delete(String key) {
		return tags.delete(key);
	}

	@Override
	public Service update(String key, Service value) {
		return tags.update(key, value);
	}

	@Override
	public void add(String key, Service value) {
		tags.add(key, value);
	}

	@Override
	public Service get(String key) {
		return tags.get(key);
	}

	@Override
	public Collection<Service> getAll() {
		return tags.getAll();
	}

	@Override
	public void init(ConcurrentHashMap<String, Service> cache) {
		tags.init(cache);
	}

	@Override
	public Collection<Service> getVisibleServicesByNode(Node node) {
		return null;
	}

}
