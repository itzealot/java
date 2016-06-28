package com.sky.projects.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StringTokenTest extends TestCase {
	public StringTokenTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(StringTokenTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	public void testTrim() {
		trim("						DDDDDDDDDDDDDD	EEEEEE");
		trim("	BBBB				DDDDDDDDDDDDDD	EEEEEE");
		trim("AAAAAAAA					DDDDDDDDDDDDDD	EEEEEE");
		trim("AAAAAAAA	BBBB			DDDDDDDDDDDDDD	EEEEEE");
		trim("AAAAAAAA							EEEEEE");
		trim("AAAAAAAA	MULL	CCCCCCCCCCC	MULL	MULL");
		trim("AAAAAAAA	BBBB			DDDDDDDDDDDDDD	EEEEEE");
		trim("AAAAAAAA	BBBB	CCCCCCCCCCC				");
		trim("AAAAAAAA	BBBB			DDDDDDDDDDDDDD		");
	}

	public void trim(String value) {
		int size = 5;
		final String defaultValue = "MULL";
		final String delim = "\t";

		// 处理第一个为空的情况
		if (value.indexOf(delim) == 0) {
			value = defaultValue + delim + value.substring(1);
		}

		String res = replace(value, delim, defaultValue);

		StringTokenizer tokenizer = new StringTokenizer(res, delim);

		List<String> reults = append(tokenizer);
		for (int i = reults.size(); i < size; i++) {
			reults.add(defaultValue);
		}

		System.out.println(reults);
	}

	public String replace(final String source, String delim, String value) {
		String oldString = delim + delim + delim;
		String newString = delim + value;

		StringBuffer buffer = new StringBuffer();
		int index = 0;
		String current = source;

		while ((index = current.indexOf(oldString)) != -1) {
			buffer.append(current.substring(0, index));
			buffer.append(newString);
			current = current.substring(index + delim.length() * 2);
		}

		return buffer.append(current).toString();
	}

	public String reverse(String source) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = source.length(); i < len; i++) {
			buffer.append(source.charAt(i));
		}

		return buffer.toString();
	}

	public List<String> append(StringTokenizer tokenizer) {
		List<String> results = new ArrayList<>();

		while (tokenizer.hasMoreTokens()) {
			results.add(tokenizer.nextToken());
		}

		return results;
	}
}
