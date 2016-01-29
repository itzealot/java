package com.zt.test;

import java.util.TreeSet;

import org.junit.Test;

public class TestFooComparator {

	@Test
	public void test() {
		TreeSet<Foo> set = new TreeSet<Foo>(new FooComparator());
		set.add(new Foo(1));
		set.add(new Foo(3));
		set.add(new Foo(2));
		set.add(new Foo(4));
		System.out.println(set);
	}
}
