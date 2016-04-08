package com.projects.sky.util.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArrayUtil {

	private int[] array = null;

	@Before
	public void before() {
		array = new int[5];
		for (int i = 0; i < 5; i++) {
			array[i] = i + 1;
		}
	}

	@After
	public void after() {
		array = null;
	}

	@Test
	public void testReverse() {
		System.out.println(Arrays2.array2String(Arrays2.reverse(array), " ", false));
	}

	@Test
	public void testReverse2() {
		System.out.println(Arrays2.array2String(Arrays2.reverse(array, 2, 4), " ", false));
	}

	@Test
	public void testGetStringFromArray() {
		Integer[] array2 = new Integer[5];

		for (int i = 0; i < 5; i++) {
			array2[i] = i + 1;
		}

		System.out.println(Arrays2.array2String(array2, " ", false));
	}
}
