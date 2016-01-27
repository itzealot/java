package com.zt.test;

import org.junit.Test;

import com.zt.test.RepeatingAnnotations.Filter;
import com.zt.test.RepeatingAnnotations.Filterable;

public class ReoeatingAnnotations {
	@Test
	public void test() {
		// getAnnotation(Filters.class) 经编译器处理后将会返回 Filters 的实例
		for (Filter f : Filterable.class.getAnnotationsByType(Filter.class)) {
			System.out.println(f.value());
		}
	}
}
