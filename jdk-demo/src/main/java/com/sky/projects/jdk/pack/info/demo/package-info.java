@PkgAnnotation(version = "1.0")
package com.sky.projects.jdk.pack.info.demo;

/**
 * package-info不是平常类，其作用有三个:<br>
 * 1、为标注在包上Annotation提供便利；<br>
 * 2、声明包的私有类和常量；<br>
 * 3、提供包的整体注释说明。<br>
 * 
 * 类
 * 
 * @author zealot
 *
 */
class PackageInfo {
	/**
	 * The common method
	 */
	public void common() {
		System.out.println("sa");
	}
}

/**
 * 泛型
 * 
 * @author zealot
 *
 * @param <T>
 */
class PackageInfoGeneric<T extends Throwable> {
	private T obj;

	public void set(T obj) {
		this.obj = obj;
	}

	public void common() {
		System.out.println(obj + "sa");
	}
}

/**
 * 接口
 * 
 * @author zealot
 *
 */
interface packageInfoInteger {
	public void test();
}

/**
 * 常量
 * 
 * @author zealot
 *
 */
class PackageConstants {
	public static final String ERROE_CODE = "100001";
}