package com.sky.projects.design.structural.proxy.dynamic;

import java.lang.reflect.Proxy;

import com.sky.projects.design.structural.proxy.dynamic.impl.QueryDaoImpl;
import com.sky.projects.design.structural.proxy.dynamic.impl.QueryServiceImpl;

/**
 * 动态代理类实现工厂
 * 
 * @author zealot
 */
public final class ProxyFactory {

	public static QueryService createQueryServiceProxy() {
		return createProxy(new QueryServiceImpl());
	}

	public static QueryDao createQueryDaoProxy() {
		return createProxy(new QueryDaoImpl());
	}

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(T target) {
		return (T) Proxy.newProxyInstance(fetchClassLoader(target.getClass()), target.getClass().getInterfaces(),
				new ProxyHandler(target));
	}

	public static ClassLoader fetchClassLoader(Class<?> clazz) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null) {
			loader = clazz.getClassLoader();
		}

		return loader == null ? ProxyFactory.class.getClassLoader() : loader;
	}

	private ProxyFactory() {
	}
}
