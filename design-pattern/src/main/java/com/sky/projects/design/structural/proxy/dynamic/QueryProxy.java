package com.sky.projects.design.structural.proxy.dynamic;

import java.lang.reflect.Proxy;

public class QueryProxy {
	private QueryDao dao;

	public QueryProxy(QueryDao dao) {
		this.dao = dao;
	}

	/**
	 * 根据被代理对象信息，生成对应的代理对象，包含相同的类加载器，实现相同的接口
	 * 
	 * @return
	 */
	public QueryDao getProxy() {
		return (QueryDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(), dao.getClass().getInterfaces(),
				new QueryProxyHandler());
	}

}
