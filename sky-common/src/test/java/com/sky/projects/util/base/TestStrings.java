package com.sky.projects.util.base;

import org.junit.Test;

import com.sky.projects.util.base.Strings;

public class TestStrings {

	@Test
	public void testStartWith() {
		String source = "I am Ok!";
		String start = "I am";
		System.out.println(Strings.startsWith(source, start));

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			Strings.startsWith(source, start);
		}

		long endTime = System.currentTimeMillis();

		System.out.println("mine method: " + (endTime - startTime));

		startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			source.startsWith(start);
		}

		endTime = System.currentTimeMillis();

		System.out.println("String method: " + (endTime - startTime));
	}

	@Test
	public void testEndWith() {
		String source = "I am Ok!";
		String end = "am Ok!";
		System.out.println(Strings.endsWith(source, end));
	}

}
