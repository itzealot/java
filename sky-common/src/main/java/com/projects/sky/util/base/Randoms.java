package com.projects.sky.util.base;

import java.util.Random;

public final class Randoms {

	private Randoms() {
	}

	private static Random get() {
		return new Random();
	}

	public static int randomInteger() {
		return get().nextInt();
	}

	private static void check(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("参数 n = " + n + " 非法，值必须大于0");
		}
	}

	public static int[] randomIntegers(int n) throws Exception {
		check(n);

		int[] result = new int[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomInteger();
		}

		return result;
	}

	public static int randomInteger(int n) {
		return get().nextInt(n);
	}

	public static int randomInteger(int start, int end) throws Exception {
		if (start >= end) {
			throw new Exception("参数start 与 end 非法");
		}

		return start + get().nextInt(end - start);
	}

	public static boolean randomBoolean() {
		return get().nextBoolean();
	}

	public static boolean[] randomBooleans(int n) throws Exception {
		check(n);

		boolean[] result = new boolean[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomBoolean();
		}

		return result;
	}

	public static float randomFloat() {
		return get().nextFloat();
	}

	public static float[] randomFloats(int n) throws Exception {
		check(n);

		float[] result = new float[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomFloat();
		}

		return result;
	}

	public static double randomDouble() {
		return get().nextDouble();
	}

	public static double[] randomDoubles(int n) throws Exception {
		check(n);

		double[] result = new double[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomDouble();
		}

		return result;
	}
}
