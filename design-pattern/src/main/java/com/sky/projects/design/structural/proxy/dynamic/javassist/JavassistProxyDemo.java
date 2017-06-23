package com.sky.projects.design.structural.proxy.dynamic.javassist;

import com.sky.projects.design.structural.proxy.dynamic.impl.QueryDaoImpl;

/**
 * dynamic proxy for javassist demo
 * 
 * @author zealot
 *
 */
public class JavassistProxyDemo {

	public static void main(String[] args) throws Exception {
		QueryDaoImpl impl = new QueryDaoImpl();
		JavassistHandler handler = new JavassistHandler(impl);

		QueryDaoImpl proxy = JavassistProxyFactory.createProxy(impl, handler);
		System.out.println(proxy.deal("msg"));
		// 创建代理时指定 superClass 创建，可以调用未抽象出接口的方法
		proxy.deal();

		Counter count = new CounterImpl();
		System.out.println(JavassistProxyFactory.createBytecodeDynamicProxy(count, Counter.class));
	}
}
