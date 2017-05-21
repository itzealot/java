package com.sky.projects.design.structural.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * 采用动态代理方式实现
 * 
 * @author zealot
 */
public class QueryProxy {

	/**
	 * 创建代理
	 * 
	 * @return
	 */
	public static QueryDao createProxy() {
		return (QueryDao) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { QueryDao.class },
				new QueryProxyHandler());
	}
}
