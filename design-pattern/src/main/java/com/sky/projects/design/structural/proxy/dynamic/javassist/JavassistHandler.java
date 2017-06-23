package com.sky.projects.design.structural.proxy.dynamic.javassist;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

/**
 * JavassistHandler
 * 
 * @author zealot
 *
 */
public class JavassistHandler implements MethodHandler {
	// 需要实现代理对象的引用
	private Object target;

	public JavassistHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		// TODO 方法调用前

		Object resutl = thisMethod.invoke(target, args);

		// TODO 方法调用后

		return resutl;
	}
}
