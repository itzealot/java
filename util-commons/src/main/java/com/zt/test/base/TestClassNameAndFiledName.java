package com.zt.test.base;

import org.junit.Test;

import com.zt.test.bean.Person;

public class TestClassNameAndFiledName {

	@Test
	public void test() {
		Person Person = new Person("1", "zhangsan", "zhangsan", "I am zhangsan");
		System.out.println(Person);
		System.out.println(Person.class);
		com.zt.test.bean.Person.test();
	}
}
