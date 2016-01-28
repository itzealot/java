package com.zt.test.impl;

import java.util.Collection;

import com.zt.test.ZkOrganizationCachable;
import com.zt.test.entity.Organization;

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
	 * 获取组织机构缓存的实例对象
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
