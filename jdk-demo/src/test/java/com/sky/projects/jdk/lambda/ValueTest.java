package com.sky.projects.jdk.lambda;

import org.junit.Test;

public class ValueTest {

	@Test
	public void test() {
		/*
		 * Value.defaultValue()的参数类型可以被推测出，所以就不必明确给出。
		 * 
		 * 在Java 7中，相同的例子将不会通过编译，正确的书写方式是 Value.<String>defaultValue()。
		 */
		System.out.println(Value.getOrDefault("22", Value.defaultValue()));
	}
}
