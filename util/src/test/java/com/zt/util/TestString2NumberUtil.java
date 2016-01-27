package com.zt.util;

import org.junit.Test;

public class TestString2NumberUtil {

	@Test
	public void test() {
		System.out.println(String2NumberUtil.getNumberFromString("",
				Integer.class));
	}

	@Test
	public void test2Integer() {
		System.out.println(String2NumberUtil.getNumberFromString("100",
				Integer.class));
	}
}
