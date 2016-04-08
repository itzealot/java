package com.projects.sky.util.base;

import org.junit.Test;

/**
 * 测试工具类 Randoms
 * 
 * @author zengtao
 *
 */
public class TestRandoms {
	@Test
	public void testRandomInteger() {
		System.out.println(Randoms.randomInteger());
		System.out.println(Randoms.randomInteger());
		System.out.println(Randoms.randomInteger());
		System.out.println(Randoms.randomInteger());
	}

	@Test
	public void testRandomIntegers() throws Exception {
		displayArray(Randoms.randomIntegers(5));
		displayArray(Randoms.randomIntegers(5));
		displayArray(Randoms.randomIntegers(5));
		displayArray(Randoms.randomIntegers(5));
	}

	@Test
	public void testRandomInteger1() {
		System.out.println(Randoms.randomInteger(5));
		System.out.println(Randoms.randomInteger(5));
		System.out.println(Randoms.randomInteger(5));
		System.out.println(Randoms.randomInteger(5));
	}

	@Test
	public void testRandomInteger2() throws Exception {
		System.out.println(Randoms.randomInteger(5, 10));
		System.out.println(Randoms.randomInteger(5, 10));
		System.out.println(Randoms.randomInteger(5, 8));
		System.out.println(Randoms.randomInteger(5, 8));
	}

	@Test
	public void testRandomBoolean() throws Exception {
		System.out.println(Randoms.randomBoolean());
		System.out.println(Randoms.randomBoolean());
		System.out.println(Randoms.randomBoolean());
		System.out.println(Randoms.randomBoolean());
	}

	@Test
	public void testRandomBooleans() throws Exception {
		displayArray(Randoms.randomBooleans(5));
		displayArray(Randoms.randomBooleans(4));
		displayArray(Randoms.randomBooleans(4));
		displayArray(Randoms.randomBooleans(5));
		displayArray(Randoms.randomBooleans(3));
	}

	private void displayArray(boolean[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}

	private void displayArray(int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}
}
