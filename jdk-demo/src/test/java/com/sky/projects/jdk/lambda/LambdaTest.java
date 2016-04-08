package com.sky.projects.jdk.lambda;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class LambdaTest {

	@Test
	public void test1() {
		// 在最简单的形式中，一个lambda可以由用逗号分隔的参数列表、–>符号与函数体三部分表示
		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
		System.out.println("========================");

		// 请注意参数e的类型是由编译器推测出来的。同时，你也可以通过把参数类型与参数包括在括号中的形式直接给出参数的类型
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));

		System.out.println("========================");

		// 函数体
		Arrays.asList("a", "b", "d").forEach(e -> {
			System.out.print(e);
			System.out.print(e);
		});

		System.out.println("\n========================");

		// Lambda可以引用类的成员变量与局部变量（如果这些变量不是final的话，它们会被隐含的转为final，这样效率更高）
		String separator1 = ",";
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator1));

		System.out.println("\n========================");

		final String separator2 = ",";
		Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator2));
	}

	@Test
	public void test2() {
		// 以下两种方法是对等的
		// 排序
		List<String> strings = Arrays.asList("a", "b", "d");
		strings.sort((e1, e2) -> e1.compareTo(e2));

		// 排序2
		List<String> strings2 = Arrays.asList("a", "b", "d");
		strings2.sort((e1, e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});

		Assert.assertEquals("array is not equal", strings, strings2);
	}

}
