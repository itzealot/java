package com.sky.projects.util.base;

import java.util.Random;

/**
 * Random Util
 * 
 * @author zealot
 */
public final class Randoms {
	private static final Random INSTANCE = new Random();

	public static int randomInteger() {
		return INSTANCE.nextInt();
	}

	public static int[] randomIntegers(int n) throws Exception {
		int[] result = new int[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomInteger();
		}

		return result;
	}

	public static int randomInteger(int n) {
		return INSTANCE.nextInt(n);
	}

	public static int randomInteger(int start, int end) throws Exception {
		if (start >= end) {
			throw new Exception("参数start 与 end 非法");
		}

		return start + INSTANCE.nextInt(end - start);
	}

	public static boolean randomBoolean() {
		return INSTANCE.nextBoolean();
	}

	public static boolean[] randomBooleans(int n) throws Exception {
		boolean[] result = new boolean[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomBoolean();
		}

		return result;
	}

	public static float randomFloat() {
		return INSTANCE.nextFloat();
	}

	public static float[] randomFloats(int n) throws Exception {
		float[] result = new float[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomFloat();
		}

		return result;
	}

	public static double randomDouble() {
		return INSTANCE.nextDouble();
	}

	public static double[] randomDoubles(int n) {
		double[] result = new double[n];

		for (int i = 0; i < n; i++) {
			result[i] = randomDouble();
		}

		return result;
	}

	private Randoms() {
	}
}
