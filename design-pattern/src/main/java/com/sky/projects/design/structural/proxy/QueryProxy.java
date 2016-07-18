package com.sky.projects.design.structural.proxy;

import com.sky.projects.design.structural.proxy.dynamic.QueryDao;
import com.sky.projects.design.structural.proxy.dynamic.supprot.QueryDaoImpl;

/**
 * 静态代理，实现被代理者相同的接口，持有被代理的引用
 * 
 * @author zt
 */
public class QueryProxy implements QueryDao {
	private QueryDao dao;

	public QueryProxy(QueryDao dao) {
		this.dao = dao;
	}

	public QueryProxy() {
	}

	@Override
	public String request() {
		return dao.request();
	}

	public String requestProxy() {
		// 在真正需要的时候才创建真实的对象，创建过程可能很慢
		if (dao == null) {
			dao = new QueryDaoImpl();
		}

		// 多线程下，此处返回一个虚假类，类似于Future
		return dao.request();
	}

}
