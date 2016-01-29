package com.zt.test.demo1;

import java.io.IOException;

/**
 * 测试package-info.java文件的作用 <br />
 * 1、为标注在包上Annotation提供便利；<br>
 * 2、声明包的私有类和常量；<br>
 * 
 * @author JoyoungZhang@gmail.com
 * 
 */
public class TestPackageInfo {

	public static void main(String[] args) {

		// To get the package object
		Package p = Package.getPackage("com.zt.test.demo1");
		/**
		 * To get the package's version
		 */
		if (p != null && p.isAnnotationPresent(PkgAnnotation.class)) {
			PkgAnnotation nav = p.getAnnotation(PkgAnnotation.class);
			if (nav != null) {
				System.out.println("package version: " + nav.version());
			}
		}
		/**
		 * 1). 对象
		 */
		// To get new Object by PackageInfo class in package-inf.java
		PackageInfo packageInfo = new PackageInfo();
		// 调用方法
		packageInfo.common();

		/**
		 * 2)泛型
		 */
		// 泛型也能很好的工作，在pakcage-info.java里定义的类和普通类没什么区别
		PackageInfoGeneric<Exception> packageInfoGeneric = new PackageInfoGeneric<Exception>();
		packageInfoGeneric.set(new IOException("device io"));

		// 调用方法
		packageInfoGeneric.common();

		/**
		 * 3). 接口
		 */
		Sub sub = new Sub();
		sub.test();

		/**
		 * 4). 常量
		 */
		System.out.println(PackageConstants.ERROE_CODE);
	}
}

/**
 * 调用package-info.java中的接口
 * 
 * @author a
 *
 */
class Sub implements packageInfoInteger {

	@Override
	public void test() {
		System.out.println("sub");
	}

}