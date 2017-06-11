package com.sky.projects.jdk.lambda;

/**
 * 使用 @FunctionalInterface 注解，如果没有方法(除去默认方法)，会报错
 * 
 * @author zt
 *
 */
@FunctionalInterface
public interface FunctionalDefaultMethods {

	void method();

	/**
	 * 接口的默认方法
	 */
	default void defaultMethod() {
		System.out.println("default method...");
	}
}