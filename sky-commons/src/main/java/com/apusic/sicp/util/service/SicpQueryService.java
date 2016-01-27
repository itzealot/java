package com.apusic.sicp.util.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.apusic.ebiz.framework.core.dao.QueryService;
import com.apusic.sicp.util.Page;
import com.apusic.sicp.util.PageQueryParameter;

/**
 * <li>功能简述: <li>详细描述:
 * 
 * @author huangtanqin
 * @create 2011-10-9
 */
public interface SicpQueryService extends QueryService {

	public Page findPage(DetachedCriteria dc, PageQueryParameter parameter);

	/**
	 * 根据sql语句查询
	 * 
	 * @param hql
	 * @param paramaters
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<?> findByHql(final String hql, final Object[] paramaters,
			final int firstResult, final int maxResult);

	/**
	 * 根据hql语句查询记录数
	 * 
	 * @param hql
	 * @param paramaters
	 * @return
	 */
	public long getLongByHql(final String hql, final Object[] paramaters);

	/**
	 * 获取数据库记录 根据DetachedCriteria
	 * 
	 * @param dc
	 * @param beginIdex
	 *            数据库开始记录
	 * @param pageSize
	 *            返回数据条数
	 * @return
	 */
	public List<?> findPage(final DetachedCriteria dc, final int beginIndex,
			final int pageSize);
}
