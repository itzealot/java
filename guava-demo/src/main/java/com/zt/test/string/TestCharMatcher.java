package com.zt.test.string;

import org.junit.Test;

import com.google.common.base.CharMatcher;

public class TestCharMatcher {
	private String string = "I love \n you 1314 []\t ";

	@Test
	public void test() {
		// 移除control字符
		String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string);
		System.out.println(noControl);
		
		// 只保留数字字符
		String theDigits = CharMatcher.DIGIT.retainFrom(string);
		System.out.println(theDigits);

		// 去除两端的空格，并把中间的连续空格替换成单个空格
		String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
		System.out.println(spaced);

		// 用*号替换所有数字
		String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*");
		System.out.println(noDigits);

		// 只保留数字和小写字母
		String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(
				CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
		System.out.println(lowerAndDigit);
	}
}
