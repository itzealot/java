package com.sky.projects.design.structural.proxy.dynamic;

/**
 * dynamic proxy demo
 * 
 * @author zealot
 *
 */
public class DynamicProxyDemo {

	public static void main(String[] args) {
		QueryDao dao = ProxyFactory.createQueryDaoProxy();

		System.out.println(dao);
		// java.lang.ClassCastException
		// dao.deal();
		System.out.println(ProxyFactory.createQueryDaoProxy());

		System.out.println(dao.request());
		System.out.println(dao.deal("msg"));

		QueryService queryService = ProxyFactory.createQueryServiceProxy();
		System.out.println(queryService);
		System.out.println(queryService.service("msg"));
	}
}
