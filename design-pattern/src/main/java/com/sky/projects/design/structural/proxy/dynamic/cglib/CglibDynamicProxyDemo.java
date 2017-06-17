package com.sky.projects.design.structural.proxy.dynamic.cglib;

import com.sky.projects.design.structural.proxy.dynamic.impl.QueryDaoImpl;
import com.sky.projects.design.structural.proxy.dynamic.impl.QueryServiceImpl;

/**
 * dynamic proxy for cglib demo
 * 
 * @author zealot
 *
 */
public class CglibDynamicProxyDemo {

	public static void main(String[] args) {
		CglibInterceptor proxy = new CglibInterceptor();

		QueryDaoImpl dao = CglibProxyFactory.createProxy(QueryDaoImpl.class, proxy);
		System.out.println(dao);

		// cglib 支持调用不是接口的方法
		dao.deal();
		System.out.println(CglibProxyFactory.createProxy(QueryDaoImpl.class, proxy));

		System.out.println(dao.request());
		System.out.println(dao.deal("msg"));

		QueryServiceImpl queryService = CglibProxyFactory.createProxy(QueryServiceImpl.class, proxy);
		System.out.println(queryService);
		System.out.println(queryService.service("msg"));
	}
}
