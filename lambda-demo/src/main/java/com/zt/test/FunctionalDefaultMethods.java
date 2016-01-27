package com.zt.test;

/**
 * 使用 @FunctionalInterface 注解，如果没有方法(出去默认方法)，会报错
 * 
 * @author zt
 *
 */
@FunctionalInterface
public interface FunctionalDefaultMethods {
	public void method();

	/**
	 * 默认方法
	 */
	default void defaultMethod() {
		System.out.println("default method...");
	}
}