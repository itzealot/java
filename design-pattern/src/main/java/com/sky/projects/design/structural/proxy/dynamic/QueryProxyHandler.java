package com.sky.projects.design.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.sky.projects.design.structural.proxy.dynamic.supprot.QueryDaoImpl;

public class QueryProxyHandler implements InvocationHandler {
	private QueryDao real;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 第一次调用则生成真实对象
		if (real == null) {
			real = new QueryDaoImpl();
		}
		// 使用真实主题完成实际的操作
		return real.request();
	}

}
