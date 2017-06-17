package com.sky.projects.design.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.sky.projects.design.structural.proxy.dynamic.impl.QueryDaoImpl;

/**
 * 动态代理实现类，根据传入的实现类反射调用
 * 
 * @author zealot
 */
public class ProxyHandler implements InvocationHandler {

	// 需要代理的实现类
	private Object target;

	public ProxyHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object arg0, Method method, Object[] args) throws Throwable {
		// TODO 方法调用前
		// 若要调用实现类未抽象出接口的方法，可以在此处实现
		if (target instanceof QueryDaoImpl) {
			((QueryDaoImpl) target).deal();
		}

		// 使用反射进行方法调用
		Object result = method.invoke(this.target, args);

		// TODO 方法调用后

		// 结果返回
		return result;
	}

}
