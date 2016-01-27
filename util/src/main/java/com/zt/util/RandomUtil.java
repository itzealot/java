package com.zt.util;

import java.util.Random;

/**
 * 封装了随机数的工具类 RandomUtil.<br />
 * 内部含有各种产生数据的方法.<br />
 * 
 * @author zengtao
 *
 */
public class RandomUtil {

	/**
	 * 获取随机数类的Random实例
	 * 
	 * @return
	 */
	private static Random getRandomInstance() {
		return new Random();
	}

	/**
	 * 随机产生一个整数并返回
	 * 
	 * @param n
	 * @return
	 */
	public static int randomInteger() {
		return getRandomInstance().nextInt();
	}

	/**
	 * 随机产生一个保存随机整数的数组，并返回
	 * 
	 * @param n
	 *            must is bigger than 0
	 * @return
	 * @throws Exception
	 */
	public static int[] randomIntegers(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}
		int[] result = new int[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomInteger();
		}
		return result;
	}

	/**
	 * 随机产生n以内的随机整数并返回
	 * 
	 * @param n
	 * @return
	 */
	public static int randomInteger(int n) {
		return getRandomInstance().nextInt(n);
	}

	/**
	 * 随机产生 start 与 end 之间的整数
	 * 
	 * @param start
	 *            the value is bigger than start
	 * @param end
	 *            the value is smaller than end
	 * @return
	 * @throws Exception
	 */
	public static int randomInteger(int start, int end) throws Exception {
		if (start >= end) {
			throw new Exception("参数start 与 end 非法");
		}

		return start + getRandomInstance().nextInt(end - start);
	}

	/**
	 * 随机产生一个boolean值
	 * 
	 * @return the boolean value
	 */
	public static boolean randomBoolean() {
		return getRandomInstance().nextBoolean();
	}

	/**
	 * 产生n个boolean值存放的数组，并返回
	 * 
	 * @param n
	 *            must is bigger than 0
	 * @return
	 * @throws Exception
	 */
	public static boolean[] randomBooleans(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}
		boolean[] result = new boolean[n];

		// 产生boolean数组
		for (int i = 0; i < n; i++) {
			result[i] = randomBoolean();
		}

		return result;
	}

	/**
	 * 产生一个float数值的数并返回
	 * 
	 * @return
	 */
	public static float randomFloat() {
		return getRandomInstance().nextFloat();
	}

	/**
	 * 产生一个含有n个随机float数值的数组并返回
	 * 
	 * @param n
	 *            must is bigger than 0
	 * @return
	 * @throws Exception
	 */
	public static float[] randomFloats(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}
		float[] result = new float[n];

		// 产生float数组
		for (int i = 0; i < n; i++) {
			result[i] = randomFloat();
		}

		return result;
	}

	/**
	 * 产生一个double数值的数并返回
	 * 
	 * @return
	 */
	public static double randomDouble() {
		return getRandomInstance().nextDouble();
	}

	/**
	 * 产生一个含有n个随机double数值的数组并返回
	 * 
	 * @param n
	 *            must is bigger than 0
	 * @return
	 * @throws Exception
	 */
	public static double[] randomDoubles(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}
		double[] result = new double[n];

		// 产生float数组
		for (int i = 0; i < n; i++) {
			result[i] = randomDouble();
		}

		return result;
	}
}
