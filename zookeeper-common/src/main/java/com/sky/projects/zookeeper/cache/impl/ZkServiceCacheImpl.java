package com.sky.projects.zookeeper.cache.impl;

import java.util.Collection;

import com.sky.projects.zookeeper.cache.ZkServiceCachable;
import com.sky.projects.zookeeper.entity.Node;
import com.sky.projects.zookeeper.entity.Service;

/**
 * 服务缓存实现类
 * 
 * @author zt
 *
 */
public class ZkServiceCacheImpl extends ZkCacheImpl<String, Service> implements ZkServiceCachable {

	private static ZkServiceCacheImpl instance = null;

	private ZkServiceCacheImpl() {
	}

	/**
	 * 获取服务缓存的实例
	 * 
	 * @return
	 */
	public static ZkServiceCacheImpl getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new ZkServiceCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public Collection<Service> getVisibleServicesByNode(Node node) {
		return null;
	}

}
