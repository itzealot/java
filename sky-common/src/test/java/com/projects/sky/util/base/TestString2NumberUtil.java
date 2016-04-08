package com.projects.sky.util.base;

import org.junit.Test;

public class TestString2NumberUtil {

	@Test
	public void test() {
		System.out.println(Strings.getNumberFromString("", Integer.class));
	}

	@Test
	public void test2Integer() {
		System.out.println(Strings.getNumberFromString("100", Integer.class));
	}
}
