package com.projects.sky.zookeeper.service;

import java.util.Collection;

import com.projects.sky.zookeeper.entity.Organization;

/**
 * Created by Colin on 2015/10/13.
 */
public interface OrganizationRegisterService {
	/**
	 * 获取所有组织机构
	 * 
	 * @return
	 */
	Collection<Organization> findAll();

	/**
	 * 注册组织机构
	 * 
	 * @param org
	 * @return
	 */
	void add(Organization org);

	/**
	 * 获取下级组织机构
	 * 
	 * @param org
	 * @return
	 */
	Collection<Organization> getChildren(Organization org);

	/**
	 * 获取上级组织机构
	 * 
	 * @param organization
	 * @return
	 */
	Organization getParent(Organization organization);

	/**
	 * 获取code对应的组织机构
	 * 
	 * @param code
	 * @return
	 */
	Organization getOrganization(String code);

	/**
	 * 删除指定的组织机构，如果组织机构存在下级机构则删除失败
	 * 
	 * @param org
	 */
	void deleteOrganization(Organization org);

	/**
	 * 更新组织机构
	 * 
	 * @param org
	 */
	void updateOrganization(Organization org);

}
