package com.sky.projects.sky.util;

import org.junit.Test;

import com.zt.projects.sky.util.RandomUtil;

/**
 * 测试工具类 RandomUtil
 * 
 * @author zengtao
 *
 */
public class TestRandomUtil {
	@Test
	public void testRandomInteger() {
		System.out.println(RandomUtil.randomInteger());
		System.out.println(RandomUtil.randomInteger());
		System.out.println(RandomUtil.randomInteger());
		System.out.println(RandomUtil.randomInteger());
	}

	@Test
	public void testRandomIntegers() throws Exception {
		displayArray(RandomUtil.randomIntegers(5));
		displayArray(RandomUtil.randomIntegers(5));
		displayArray(RandomUtil.randomIntegers(5));
		displayArray(RandomUtil.randomIntegers(5));
	}

	@Test
	public void testRandomInteger1() {
		System.out.println(RandomUtil.randomInteger(5));
		System.out.println(RandomUtil.randomInteger(5));
		System.out.println(RandomUtil.randomInteger(5));
		System.out.println(RandomUtil.randomInteger(5));
	}

	@Test
	public void testRandomInteger2() throws Exception {
		System.out.println(RandomUtil.randomInteger(5, 10));
		System.out.println(RandomUtil.randomInteger(5, 10));
		System.out.println(RandomUtil.randomInteger(5, 8));
		System.out.println(RandomUtil.randomInteger(5, 8));
	}

	@Test
	public void testRandomBoolean() throws Exception {
		System.out.println(RandomUtil.randomBoolean());
		System.out.println(RandomUtil.randomBoolean());
		System.out.println(RandomUtil.randomBoolean());
		System.out.println(RandomUtil.randomBoolean());
	}

	@Test
	public void testRandomBooleans() throws Exception {
		displayArray(RandomUtil.randomBooleans(5));
		displayArray(RandomUtil.randomBooleans(4));
		displayArray(RandomUtil.randomBooleans(4));
		displayArray(RandomUtil.randomBooleans(5));
		displayArray(RandomUtil.randomBooleans(3));
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
