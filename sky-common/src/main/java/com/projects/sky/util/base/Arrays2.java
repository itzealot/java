package com.projects.sky.util.base;

import java.util.ArrayList;
import java.util.List;

public final class Arrays2 {

	private Arrays2() {
	}

	public static String array2String(boolean[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(byte[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(short[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(char[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(int[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(float[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;
		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(long[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static String array2String(double[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i]);
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static <E> String array2String(E[] array, String split, boolean flag) {
		if (!Validates.validate(array)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int length = array.length;

		for (int i = 0; i < length; i++) {
			buffer.append(array[i].toString());
			buffer.append(split);
		}

		if (flag) {
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 * @return the reverse array.
	 */
	public static <E> E[] reverse(E[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			E temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the E[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static <E> E[] reverse(E[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}
		int length = array.length;

		// The start index is smaller than 0 then throw exception
		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		// The end index is beyond the length then throw exception
		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		// To reverse the array
		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			E temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the boolean[] array
	 * @return the reverse array.
	 */
	public static boolean[] reverse(boolean[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			boolean temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the byte[] array
	 * @return the reverse array.
	 */
	public static byte[] reverse(byte[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			byte temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            short char[] array
	 * @return the reverse array.
	 */
	public static short[] reverse(short[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			short temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the char[] array
	 * @return the reverse array.
	 */
	public static char[] reverse(char[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			char temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the int[] array
	 * @return the reverse array.
	 */
	public static int[] reverse(int[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the float[] array
	 * @return the reverse array.
	 */
	public static float[] reverse(float[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;
		for (int i = 0; i < times; i++) {
			float temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the long[] array
	 * @return the reverse array.
	 */
	public static long[] reverse(long[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			long temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the double[] array
	 * @return the reverse array.
	 */
	public static double[] reverse(double[] array) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			double temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the boolean[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static boolean[] reverse(boolean[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			boolean temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the byte[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static byte[] reverse(byte[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			byte temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the short[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static short[] reverse(short[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			short temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the char[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static char[] reverse(char[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			char temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the int[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static int[] reverse(int[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}
		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			int temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the float[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static float[] reverse(float[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			float temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the long[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static long[] reverse(long[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			long temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 *            the double[] array
	 * @param start
	 *            the start index
	 * @param end
	 *            the end index
	 * @return the reverse array.
	 */
	public static double[] reverse(double[] array, int start, int end) {
		if (!Validates.validate(array)) {
			return array;
		}

		int length = array.length;

		if (start < 0) {
			throw new ArrayIndexOutOfBoundsException(start);
		}

		if (end >= length) {
			throw new ArrayIndexOutOfBoundsException(end);
		}

		length = end - start + 1;
		int times = length / 2;

		for (int i = 0; i < times; i++) {
			int from = i + start;
			int to = length + start - i - 1;
			double temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}

		return array;
	}

	public static <T> List<T> range(List<T> lists, int start, int end) {
		List<T> newLists = new ArrayList<>();

		if (!Validates.validate(lists)) {
			return newLists;
		}

		int from = start < 0 ? 0 : start;

		int len = lists.size();
		int to = end > len ? len : end;

		for (int i = from; i < to; i++) {
			newLists.add(lists.get(i));
		}

		return newLists;
	}

}
