package com.sky.projects.util.base;

import org.junit.Test;

import com.sky.projects.util.base.Strings;

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
