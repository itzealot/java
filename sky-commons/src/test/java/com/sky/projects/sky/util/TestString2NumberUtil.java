package com.sky.projects.sky.util;

import org.junit.Test;

import com.zt.projects.sky.util.String2NumberUtil;

public class TestString2NumberUtil {

	@Test
	public void test() {
		System.out.println(String2NumberUtil.getNumberFromString("", Integer.class));
	}

	@Test
	public void test2Integer() {
		System.out.println(String2NumberUtil.getNumberFromString("100", Integer.class));
	}
}
