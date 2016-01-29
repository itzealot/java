package com.apusic.sicp.util.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.apusic.ebiz.framework.core.dao.CrudServiceHibernateImpl;
import com.apusic.sicp.util.service.SicpCrudService;

/**
 * 
 * @author zt
 *
 */
public class SicpCrudServiceImpl extends CrudServiceHibernateImpl implements
		SicpCrudService {

	private HibernateTemplate sicpHibernateTemplate;

	public SicpCrudServiceImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sicpHibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void remove(final String hql, final List<Object> params) {
		sicpHibernateTemplate.execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
				return query.executeUpdate();
			}
		});
	}

	@Override
	public void updateBySql(final String hql, final Object[] parameters) {
		sicpHibernateTemplate.execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return createQuery(hql, parameters, session).executeUpdate();
			}
		});
	}

	private Query createQuery(final String hql, final Object[] parameters,
			Session session) {
		Query query = session.createQuery(hql);
		for (int i = 0; parameters != null && i < parameters.length; i++) {
			query.setParameter(i, parameters[i]);
		}
		return query;
	}
}
