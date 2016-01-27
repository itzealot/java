package com.zt.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexMatches {

	@Test
	public void test1() {

		// 按指定模式在字符串查找
		String line = "This order was placed for QT3000! OK?";
		String pattern = "(.*)(\\d+)(.*)";

		// 根据正则表达式创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 根据需要匹配的字符串创建 matcher 对象
		Matcher m = r.matcher(line);

		int count = m.groupCount();
		System.out.println("group count: " + count);

		// 是否匹配到
		if (m.find()) {
			for (int i = 0; i < count; i++) {
				System.out.println("Found value: " + m.group(i));
			}
		} else {
			System.out.println("NO MATCH");
		}
	}

	// 匹配cat
	private static final String REGEX = "\\bcat\\b";
	private static final String INPUT = "cat cat cat cattie cat";

	/**
	 * m.start() : 正则表达式匹配到当前组的开始字符.<br />
	 * m.end() ： 正则表达式匹配到当前组的结束字符 +1 的位置.<br />
	 */
	@Test
	public void test2() {

		// 获取 Pattern 对象
		Pattern p = Pattern.compile(REGEX);

		// 获取 matcher 对象
		Matcher m = p.matcher(INPUT);

		// 计数有多少个组
		int count = 0;
		while (m.find()) {
			count++;
			System.out.println("Match number " + count);
			System.out.println("start : " + m.start());
			System.out.println("end : " + m.end());
			System.out.println("match string: " + INPUT.substring(m.start(), m.end()));
			System.out.println("======================");
		}
	}

	private static final String REGEX3 = "foo";
	private static final String INPUT3 = "fooooooooooooooooo";

	@Test
	public void test3() {
		Pattern pattern = Pattern.compile(REGEX3);
		Matcher matcher = pattern.matcher(INPUT3);

		// matcher要求整个序列都匹配，而lookingAt 不要求
		System.out.println("lookingAt : " + matcher.lookingAt());
		System.out.println(matcher.groupCount());

		System.out.println("matches : " + matcher.matches());
		System.out.println(matcher.groupCount());
	}

	private static String REGEX4 = "dog";
	private static String INPUT4 = "The dog says meow. " + "All dogs say meow.";
	private static String REPLACE4 = "cat";

	/**
	 * replaceFirst 和replaceAll 方法用来替换匹配正则表达式的文本。<br />
	 * 不同的是，replaceFirst 替换首次匹配，replaceAll 替换所有匹配。<br />
	 */
	@Test
	public void test4() {
		Pattern p = Pattern.compile(REGEX4);
		Matcher m = p.matcher(INPUT4);

		// 替换所有
		System.out.println(m.replaceAll(REPLACE4));
		System.out.println(m.replaceFirst(REPLACE4));
	}

	private static String REGEX5 = "a*b";
	private static String INPUT5 = "aabfooaabfooabfoob";
	private static String REPLACE5 = "-";

	/**
	 * Matcher 类也提供了appendReplacement 和appendTail 方法用于文本替换：
	 */
	@Test
	public void test5() {
		Pattern p = Pattern.compile(REGEX5);
		Matcher m = p.matcher(INPUT5);

		// 遍历匹配到的组,实现替换所有
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, REPLACE5);
		}
		m.appendTail(sb);
		System.out.println(sb.toString());

		// 替换所有
		System.out.println(m.replaceAll(REPLACE5));
	}
}