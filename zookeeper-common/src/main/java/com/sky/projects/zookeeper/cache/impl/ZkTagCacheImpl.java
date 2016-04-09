package com.sky.projects.zookeeper.cache.impl;

import com.sky.projects.zookeeper.cache.ZkTagCachable;
import com.sky.projects.zookeeper.entity.TagCatalog;

/**
 * 服务标签缓存实现类
 * 
 * @author zt
 *
 */
public class ZkTagCacheImpl extends ZkCacheImpl<String, TagCatalog> implements ZkTagCachable {

	private static ZkTagCacheImpl instance = null;

	private ZkTagCacheImpl() {
	}

	/**
	 * 获取服务分类缓存的实例
	 * 
	 * @return
	 */
	public static ZkTagCacheImpl getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new ZkTagCacheImpl();
			}
		}

		return instance;
	}

}
