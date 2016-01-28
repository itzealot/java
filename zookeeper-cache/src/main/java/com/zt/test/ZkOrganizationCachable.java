package com.zt.test;

import java.util.Collection;

import com.zt.test.entity.Organization;

/**
 * 组织机构缓存接口
 * 
 * @author zt
 *
 */
public interface ZkOrganizationCachable extends ZkCachable<String, Organization> {

	/**
	 * 根据给予的组织机构获取父组织机构
	 * 
	 * @param org
	 * @return
	 */
	public Organization getParent(Organization org);

	/**
	 * 根据给予的组织机构获取所有的下属组织机构
	 * 
	 * @param t
	 * @return
	 */
	public Collection<Organization> getChildren(Organization org);
}
