package com.sky.projects.design.structural.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 
 * @author zealot
 *
 */
public final class CglibProxyFactory {

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Class<T> clazz, MethodInterceptor interceptor) {
		Enhancer enhancer = new Enhancer();

		// 设置需要创建子类的类，即实现类的字节码构造，类中的方法都可以调用
		enhancer.setSuperclass(clazz);

		// 若设置的是接口，则返回的是接口的实现类，类中未抽象接口的方法不允许调用
		// enhancer.setInterfaces(clazz.getInterfaces());

		enhancer.setCallback(interceptor);

		// 通过字节码技术动态创建子类实例
		return (T) enhancer.create();
	}

	private CglibProxyFactory() {
	}
}
