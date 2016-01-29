package com.apusic.sicp.util.service;

import java.util.List;

import com.apusic.ebiz.framework.core.dao.CrudService;

/**
 * 
 * @author zt
 *
 */
public interface SicpCrudService extends CrudService {
	/**
	 * 根据hql语句批量删除
	 * 
	 * @param hql
	 */
	public void remove(final String hql, final List<Object> params);

	/**
	 * 根据hql语句批量修改
	 * 
	 * @param hql
	 * @param parameters
	 */
	public void updateBySql(final String hql, Object[] parameters);
}
