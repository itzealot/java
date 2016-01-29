package com.apusic.sicp.util.service.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.apusic.ebiz.framework.core.dao.QueryServiceHibernateImpl;
import com.apusic.sicp.SicpRuntimeException;
import com.apusic.sicp.util.Page;
import com.apusic.sicp.util.PageQueryParameter;
import com.apusic.sicp.util.service.SicpQueryService;

/**
 * 
 * @author zt
 *
 */
public class SicpQueryServiceImpl extends QueryServiceHibernateImpl implements
		SicpQueryService {

	private HibernateTemplate sicpHibernateTemplate;

	public SicpQueryServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sicpHibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public Page findPage(final DetachedCriteria dc,
			final PageQueryParameter pageParameter) {
		final Page page = new Page(pageParameter);
		sicpHibernateTemplate.execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int firstIndex = pageParameter.getFirstIndex();
				int pageSize = pageParameter.getPageSize();
				List<?> list = dc.getExecutableCriteria(session)
						.setFirstResult(firstIndex).setMaxResults(pageSize)
						.list();
				page.setRecords(list);
				clearOrders(dc);
				Integer total = (Integer) dc.getExecutableCriteria(session)
						.setFirstResult(0).setMaxResults(1)
						.setProjection(Projections.rowCount()).uniqueResult();
				page.setTotal(total == null ? 0 : total);
				return page;
			}
		});
		return page;
	}

	@Override
	public List<?> findPage(final DetachedCriteria dc, final int beginIndex,
			final int pageSize) {
		return (List<?>) sicpHibernateTemplate
				.execute(new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return dc.getExecutableCriteria(session)
								.setFirstResult(beginIndex)
								.setMaxResults(pageSize).list();
					}
				});
	}

	private void clearOrders(final DetachedCriteria dc) {
		try {
			Field originalCriteriaField = dc.getClass().getDeclaredField(
					"criteria");
			originalCriteriaField.setAccessible(true);
			Criteria criteria = (Criteria) originalCriteriaField.get(dc);
			Field orderEntriesField = criteria.getClass().getDeclaredField(
					"orderEntries");
			orderEntriesField.setAccessible(true);
			List<?> orders = (List<?>) orderEntriesField.get(criteria);
			orders.clear();
		} catch (Exception e) {
			throw new SicpRuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public List<?> findByHql(final String hql, final Object[] paramaters,
			final int firstResult, final int maxResult) {
		List<?> results = sicpHibernateTemplate
				.executeFind(new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query q = session.createQuery(hql);
						for (int i = 0; i < paramaters.length; i++) {
							q.setParameter(i, paramaters[i]);
						}
						q.setFirstResult(firstResult);
						q.setMaxResults(maxResult);
						return q.list();
					}
				});
		return results;
	}

	@Override
	public long getLongByHql(final String hql, final Object[] paramaters) {
		Long count = (Long) sicpHibernateTemplate
				.execute(new HibernateCallback<Object>() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query q = session.createQuery(hql);
						for (int i = 0; i < paramaters.length; i++) {
							q.setParameter(i, paramaters[i]);
						}
						Long result = (Long) q.uniqueResult();
						return result != null ? result : 0l;
					}
				});
		return count;
	}

}
