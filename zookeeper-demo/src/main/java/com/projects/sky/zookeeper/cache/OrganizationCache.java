package com.projects.sky.zookeeper.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.listener.CacheListener;
import com.projects.sky.zookeeper.monitor.OrganizationMonitor;
import com.projects.sky.zookeeper.register.OrganizationRegister;

/**
 * Created by Colin on 2015/11/24. 组织机构缓存
 */
public class OrganizationCache {
	private final Logger log = LoggerFactory.getLogger(OrganizationCache.class);

	private static OrganizationCache cache = null;
	// Organization Cache
	private Map<String, Organization> organizations;

	private CacheListener<Organization> listener;

	private OrganizationCache() {
		log.info("===== new organization cache ==== {}", this);
		organizations = new LinkedHashMap<>();
		listener = new OrganizationRegister();
		new OrganizationMonitor(organizations);
		init(organizations);

	}

	public static synchronized OrganizationCache getOrganizationCache() {
		if (cache == null) {
			cache = new OrganizationCache();
		}
		return cache;
	}

	/**
	 * 系统启动时，初始化缓存
	 */
	private void init(Map<String, Organization> cache) {
		listener.init(cache);
	}

	public Collection<Organization> getAll() {
		return organizations.values();
	}

	public void add(Organization organization) {
		organizations.put(organization.getCode(), organization);
		listener.remoteAdd(organization);
	}

	public void update(Organization organization) {
		organizations.put(organization.getCode(), organization);
		listener.remoteUpdate(organization);
	}

	public void delete(Organization organization) {
		organizations.remove(organization.getCode());
		listener.remoteDelete(organization);
	}

	public Organization get(String code) {
		return organizations.get(code);
	}

	public Collection<Organization> getChildren(Organization organization) {
		String parent = organization.getCode();
		Collection<Organization> children = new ArrayList<>();
		for (String code : organizations.keySet()) {
			if (!code.equals(parent) && code.contains(parent)) {
				children.add(organizations.get(code));
			}
		}
		return children;
	}

	public Organization getParent(Organization organization) {
		String code = organization.getCode();
		int index = code.lastIndexOf("C");
		if (index < 0) {
			return null;
		}
		String parent = code.substring(0, index - 1);
		return organizations.get(parent);

	}

}
