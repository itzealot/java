package com.projects.sky.util.base;

import org.junit.Test;

public class TestString2NumberUtil {

	@Test
	public void test() {
		System.out.println(Strings.numberOf("", Integer.class));
	}

	@Test
	public void test2Integer() {
		System.out.println(Strings.numberOf("100", Integer.class));
	}
}
