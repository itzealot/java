package com.sky.projects.design.structural.proxy.dynamic.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CglibProxy
 * 
 * @author zealot
 *
 */
public class CglibInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO 方法调用前
		// System.out.println("method:" + method.getName());

		// 通过代理类调用父类中的方法
		Object result = proxy.invokeSuper(obj, args);

		// TODO 方法调用后

		return result;
	}
}