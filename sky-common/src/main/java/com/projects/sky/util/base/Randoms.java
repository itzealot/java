package com.projects.sky.util.base;

import java.util.Random;

public final class Randoms {

	private Randoms() {
	}

	private static Random getRandomInstance() {
		return new Random();
	}

	public static int randomInteger() {
		return getRandomInstance().nextInt();
	}

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

	public static int randomInteger(int n) {
		return getRandomInstance().nextInt(n);
	}

	public static int randomInteger(int start, int end) throws Exception {
		if (start >= end) {
			throw new Exception("参数start 与 end 非法");
		}

		return start + getRandomInstance().nextInt(end - start);
	}

	public static boolean randomBoolean() {
		return getRandomInstance().nextBoolean();
	}

	public static boolean[] randomBooleans(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}

		boolean[] result = new boolean[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomBoolean();
		}

		return result;
	}

	public static float randomFloat() {
		return getRandomInstance().nextFloat();
	}

	public static float[] randomFloats(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}
		float[] result = new float[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomFloat();
		}

		return result;
	}

	public static double randomDouble() {
		return getRandomInstance().nextDouble();
	}

	public static double[] randomDoubles(int n) throws Exception {
		if (n <= 0) {
			throw new Exception("参数 n 非法，值必须大于0");
		}

		double[] result = new double[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomDouble();
		}

		return result;
	}
}
