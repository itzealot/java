package com.zt.test.util;

import java.util.Arrays;

import org.junit.Test;

import com.zt.test.util.CodeFormatterUtil;

public class TestCodeFormatterUtil {
	String string = "10101010-1";
	String pad = "-C-";

	@Test
	public void padIn() {
		System.out.println(CodeFormatterUtil.padIn(string, pad, Arrays.asList(1, 3, 5)));
		System.out.println(CodeFormatterUtil.padIn("10101010", pad, Arrays.asList(1, 3, 5)));
		System.out.println(CodeFormatterUtil.padIn("BA101010", pad, Arrays.asList(1, 3, 5)));
		System.out.println(CodeFormatterUtil.padIn("BA101010", pad, Arrays.asList(1, 3, 5)).replace('-', '/'));
	}
}
