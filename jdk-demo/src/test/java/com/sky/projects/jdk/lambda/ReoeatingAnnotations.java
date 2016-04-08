package com.sky.projects.jdk.lambda;

import org.junit.Test;

import com.sky.projects.jdk.lambda.RepeatingAnnotations.Filter;
import com.sky.projects.jdk.lambda.RepeatingAnnotations.Filterable;

public class ReoeatingAnnotations {
	@Test
	public void test() {
		// getAnnotation(Filters.class) 经编译器处理后将会返回 Filters 的实例
		for (Filter f : Filterable.class.getAnnotationsByType(Filter.class)) {
			System.out.println(f.value());
		}
	}
}
