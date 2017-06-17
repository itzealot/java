package com.sky.projects.design.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookFacadeProxy implements InvocationHandler {
	private Object target;

	/**
	 * 绑定委托对象并返回一个代理类
	 * 
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T bind(T target) {
		this.target = target;
		// 取得代理对象，要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO before

		// 执行方法
		Object result = method.invoke(target, args);

		// TODO after
		return result;
	}

}