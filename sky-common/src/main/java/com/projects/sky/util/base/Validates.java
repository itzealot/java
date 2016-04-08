package com.projects.sky.util.base;

import java.util.Collection;
import java.util.Map;

public final class Validates {

	private Validates() {
	}

	public static boolean validate(boolean[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(byte[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(short[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean vlidate(char[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(float[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(long[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(double[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static <E> boolean validate(E[] array) {
		if (array == null || array.length == 0) {
			return false;
		}

		return true;
	}

	public static boolean validate(Object object) {
		if (object == null) {
			return false;
		}

		return true;
	}

	public static boolean validate(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}

		return true;
	}

	public static <E> boolean validate(Collection<E> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}

		return true;
	}

	public static <E, V> boolean validate(Map<E, V> map) {
		if (map == null || map.isEmpty()) {
			return false;
		}

		return true;
	}
}
