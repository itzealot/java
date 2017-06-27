package com.sky.projects.jdk.notes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class RegexTest extends TestCase {

	Pattern p = Pattern.compile("\\d+");

	/**
	 * 使用()来区别组,group(0)<=>group()
	 */
	public void testGroup() {
		// 两个组,组A:([a-z]+),组B:(\\d+)
		final Pattern p = Pattern.compile("([a-z]+)(\\d+)");
		System.out.println(p.matcher("1").matches());

		Matcher m = p.matcher("i love123, mis456, hate789");

		// 遍历匹配项，使用正则表达式可能存在多个匹配成功
		while (m.find()) {
			parintGroupInfo(m);
		}

		// groupCount=2
		System.out.println("groupCount:" + m.groupCount());
	}

	/**
	 * 打印对于组的匹配信息
	 * 
	 * @param m
	 */
	private void parintGroupInfo(Matcher m) {
		for (int i = 0; i <= m.groupCount(); i++) {
			System.out.println("m.group(" + i + "):" + m.group(i));
		}
		System.out.println("----------");
	}

	/**
	 * 在表达式 ((A)(B(C)))中，存在四个这样的组: <br>
	 * 1.((A)(B(C))) <br>
	 * 2.(A) <br>
	 * 3.(B(C)) <br>
	 * 4.(C) <br>
	 * 
	 * 组零始终代表整个表达式。 以 (?) 开头的组是纯的非捕获组，它不捕获文本，也不针对组合计进行计数。
	 */
	public void testGroup2() {
		// 数字个数3-5，字母个数为2，表达式分为组A(\\d{3,5}),组B([a-z]{2})
		Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
		Matcher m = p.matcher("love22-2222love-222uu-22");

		while (m.find()) { // has find
			parintGroupInfo(m);
		}

		// groupCount=2
		System.out.println("groupCount=" + m.groupCount());
	}

	/**
	 * Pattern.split : like String.split
	 */
	public void testSplit() {
		// 尾部空白符不算
		String[] splits = p.split("i love 123, mis 456, hate 789");
		// len:3
		System.out.println("len:" + splits.length);
		for (String str : splits) {
			System.out.println(str);
		}

		System.out.println("-------");
		// 尾部空白符也算
		splits = p.split("i love 123, mis 456, hate 789", -1);
		// len:4
		System.out.println("len:" + splits.length);
		for (String str : splits) {
			System.out.println(str);
		}
	}

	/**
	 * Matcher.matcher : 匹配整个字符串
	 */
	public void testMatcher() {
		System.out.println(p.matcher("love22").matches()); // false
		System.out.println(p.matcher("22").matches()); // true
	}

	/**
	 * Matcher.lookingAt : 对前面的字符串进行匹配,只有匹配到的字符串在最前面才返回 true
	 */
	public void testLookingAt() {
		System.out.println(p.matcher("22love").lookingAt()); // true
		System.out.println(p.matcher("love22").lookingAt()); // false
	}

	/**
	 * Matcher.find : 对字符串进行匹配,匹配到的字符串可以在任何位置.
	 */
	public void testFind() {
		System.out.println(p.matcher("love22u").find()); // true
		System.out.println(p.matcher("love22").find()); // true
		System.out.println(p.matcher("love").find()); // false
	}

	/**
	 * start : 返回匹配到的子字符串在字符串中的索引位置. <br>
	 * end : 返回匹配到的子字符串的最后一个字符在字符串中的索引位置. <br>
	 * group : 返回匹配到的子字符串 <br>
	 */
	public void testStart() {
		Matcher m = p.matcher("22love22love");

		System.out.println("find:");
		while (m.find()) { // 多个
			printMatcher(m);
			System.out.println("--------");
		}

		System.out.println("lookingAt:");
		m.lookingAt();
		printMatcher(m);
	}

	private void printMatcher(Matcher m) {
		System.out.println("start:" + m.start());
		System.out.println("end:" + m.end());
		System.out.println("group:" + m.group());
	}
}
