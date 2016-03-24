package com.projects.sky.zookeeper.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.projects.sky.zookeeper.cache.OrganizationCache;
import com.projects.sky.zookeeper.entity.Organization;
import com.projects.sky.zookeeper.service.OrganizationRegisterService;

/**
 * Created by colin on 2015/11/25.
 *
 */
@Service("organizationRegisterService")
public class OrganizationRegisterServiceImpl implements OrganizationRegisterService {

	private static OrganizationCache cache = OrganizationCache.getOrganizationCache();

	@Override
	public Collection<Organization> findAll() {
		return cache.getAll();
	}

	@Override
	public void add(Organization org) {
		cache.add(org);
	}

	@Override
	public Collection<Organization> getChildren(Organization org) {
		return cache.getChildren(org);
	}

	@Override
	public Organization getParent(Organization organization) {
		return cache.getParent(organization);
	}

	@Override
	public Organization getOrganization(String code) {
		return cache.get(code);
	}

	@Override
	public void deleteOrganization(Organization organization) {
		cache.delete(organization);
	}

	@Override
	public void updateOrganization(Organization organization) {
		cache.update(organization);
	}
}
