package com.sky.projects.jdk.lambda;

/**
 * Java8 允许接口实现方法， 而不是简单的声明， 这些方法叫做默认方法，使用特殊的关键字 default
 * 
 * @author zealot
 *
 */
@FunctionalInterface
interface InterfaceWithDefaultMethod {

	void apply(Object obj);

	default void say(String name) {
		System.out.println("hello " + name);
	}
}