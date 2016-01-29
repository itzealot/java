package com.zt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestList {

	@Test
	public void testForEach() {
		List<String> strings = new ArrayList<String>();

		System.out.println("----------------------------");
		for (String string : strings) {
			System.out.println(string);
		}
		System.out.println("----------------------------");
	}
}
