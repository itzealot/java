package com.zt.test.impl;

import java.util.Collection;

import com.zt.test.ZkServiceCachable;
import com.zt.test.entity.Node;
import com.zt.test.entity.Service;

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
	 * 获取组织机构缓存的实例对象
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
