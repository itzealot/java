package com.zt.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
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
		System.out.println(Array2StringUtil.getStringFromArray(
				ArrayUtil.reverse(array), " ", false));
	}

	@Test
	public void testReverse2() {
		System.out.println(Array2StringUtil.getStringFromArray(
				ArrayUtil.reverse(array, 2, 4), " ", false));
	}

	@Test
	public void testGetStringFromArray() {
		Integer[] array2 = new Integer[5];
		for (int i = 0; i < 5; i++) {
			array2[i] = i + 1;
		}
		System.out.println(Array2StringUtil.getStringFromArray(array2, " ",
				false));
	}
}
