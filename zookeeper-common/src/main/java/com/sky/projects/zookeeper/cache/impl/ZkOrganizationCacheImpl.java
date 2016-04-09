package com.sky.projects.zookeeper.cache.impl;

import java.util.Collection;

import com.sky.projects.zookeeper.cache.ZkOrganizationCachable;
import com.sky.projects.zookeeper.entity.Organization;

/**
 * 组织机构缓存实现类
 * 
 * @author zt
 *
 */
public class ZkOrganizationCacheImpl extends ZkCacheImpl<String, Organization> implements ZkOrganizationCachable {

	private static ZkOrganizationCacheImpl instance = null;

	private ZkOrganizationCacheImpl() {
	}

	/**
	 * 获取组织机构缓存的实例
	 * 
	 * @return
	 */
	public static ZkOrganizationCacheImpl getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new ZkOrganizationCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public Organization getParent(Organization org) {
		return null;
	}

	@Override
	public Collection<Organization> getChildren(Organization org) {
		return null;
	}

}
