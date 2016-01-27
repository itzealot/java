package com.zt.test;

import java.lang.annotation.Annotation;

import org.junit.Test;

public class Client {

	/**
	 * 这个比较简单，而且很实用，比如一个包中有很多的内部访问的类或常量，就可以统一的放到package-info类中，<br />
	 * 这样就方便，而且集中管理，减少friendly类到处游走的情况
	 */
	@Test
	public void testPackage() {
		// 可以通过I/O操作或配置项获得包名
		String pkgName = "com.zt.test";

		// 根据包名获取包对象
		Package pkg = Package.getPackage(pkgName);

		// 获得包上的注解
		Annotation[] annotations = pkg.getAnnotations();

		// 遍历注解数组
		for (Annotation an : annotations) {
			// 可以实例化为PkgAnnotation 注解对象
			if (an instanceof PkgAnnotation) {
				System.out.println("Hi, I'm the PkgAnnotation");
			}
		}
	}

	/**
	 * 如果是分“包”开发，也就是说一个包实现一个业务逻辑或功能点、或模块、或组件，则需要对一个包有很好的说明，<br />
	 * 说明这个包是干啥的，有啥作用，版本变迁，特别说明等等<br />
	 */
	@Test
	public void test() {

	}
}