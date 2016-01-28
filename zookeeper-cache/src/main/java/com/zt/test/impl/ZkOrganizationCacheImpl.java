package com.zt.test.impl;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import com.zt.test.ZkCachable;
import com.zt.test.ZkOrganizationCachable;
import com.zt.test.entity.Organization;

/**
 * 组织机构缓存实现类
 * 
 * @author zt
 *
 */
public class ZkOrganizationCacheImpl implements ZkOrganizationCachable {

	private static ZkCachable<String, Organization> orgs = null;

	private static ZkOrganizationCachable instance = null;

	private static Object lock = new Object();

	private ZkOrganizationCacheImpl() {
	}

	/**
	 * 获取组织机构缓存的实例对象
	 * 
	 * @return
	 */
	public static ZkOrganizationCachable getInstance() {
		if (instance == null) {
			synchronized (lock) {
				orgs = new ZkCacheImpl<String, Organization>();

				instance = new ZkOrganizationCacheImpl();
			}
		}

		return instance;
	}

	@Override
	public Organization delete(String key) {
		return orgs.delete(key);
	}

	@Override
	public Organization update(String key, Organization value) {
		return orgs.update(key, value);
	}

	@Override
	public void add(String key, Organization value) {
		orgs.add(key, value);
	}

	@Override
	public Organization get(String key) {
		return orgs.get(key);
	}

	@Override
	public Collection<Organization> getAll() {
		return orgs.getAll();
	}

	@Override
	public void init(ConcurrentHashMap<String, Organization> cache) {
		orgs.init(cache);
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
