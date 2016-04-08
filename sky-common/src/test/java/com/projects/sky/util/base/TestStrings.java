package com.projects.sky.util.base;

import java.util.List;

import org.junit.Test;

public class TestStrings {

	@Test
	public void testSplit() {
		List<String> strings = Strings.split("I love you! !", ' ');
		display(strings);
		display(Strings.split("a, b, c, d", ','));
	}

	@Test
	public void testSplit2() {
		display(Strings.split("a, b, c, d", ',', true));
		System.out.println("=========================");

		display(Strings.split("a, b, c, d", ',', false));

		System.out.println("=========================");
		String source = "a, b, c, d";
		String split = ",";
		display(Strings.split(source, split, true));
		System.out.println(source);
	}

	@Test
	public void testStartWith() {
		String source = "I am Ok!";
		String start = "I am";
		System.out.println(Strings.startWith(source, start));

		long startTime = System.currentTimeMillis();

		for (int i = 0; i < 1000000; i++) {
			Strings.startWith(source, start);
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
		System.out.println(Strings.endWith(source, end));
	}

	private void display(List<String> strings) {
		int length = strings.size();
		for (int i = 0; i < length; i++) {
			System.out.println(strings.get(i));
		}
	}
}
