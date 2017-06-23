package com.sky.projects.jdk.lambda;

import java.util.Arrays;

/**
 * 函数式接口中除了那个抽象方法外还可以包含静态方法。
 * 
 * Java 8以前的规范中接口中不允许定义静态方法。 静态方法只能在类中定义。 Java 8中可以定义静态方法。
 * 
 * @author zealot
 *
 */
@FunctionalInterface
interface FunctionalInterfaceWithStaticMethod {

	static int sum(int[] array) {
		return Arrays.stream(array).reduce((a, b) -> a + b).getAsInt();
	}

	void apply();
}