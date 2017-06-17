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

	private Object target;

	public JavassistHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		return thisMethod.invoke(target, args);
	}

}
