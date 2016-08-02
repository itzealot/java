package com.sky.projects.util.base;

import java.util.ArrayList;
import java.util.List;

public final class Arrays2 {

	public static String array2String(final boolean[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final boolean[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final byte[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final byte[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final char[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final char[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final short[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final short[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final int[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final int[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final float[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final float[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final long[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final long[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static String array2String(final double[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static String array2String_(final double[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	public static <E> String array2String(final E[] array, final String spliter, final boolean newLine) {
		return Validates.isEmpty(array) ? "" : array2String_(array, spliter, newLine);
	}

	private static <E> String array2String_(final E[] array, final String spliter, final boolean newLine) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = array.length; i < len; i++) {
			buffer.append(array[i]);
			buffer.append(spliter);
		}

		return newLine ? buffer.append("\n").toString() : buffer.toString();
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 * @return the reverse array.
	 */
	public static <E> E[] reverse(E[] array) {
		return Validates.isEmpty(array) ? array : reverse_(array);
	}

	/**
	 * 返回反转之后的数组.
	 * 
	 * @param array
	 * @return the reverse array.
	 */
	private static <E> E[] reverse_(E[] array) {
		for (int i = 0, length = array.length, times = length / 2; i < times; i++) {
			E temp = array[i];
			int target = length - i - 1;
			array[i] = array[target];
			array[target] = temp;
		}

		return array;
	}

	public static <E> E[] reverse(E[] array, int start, int end) {
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

		// To reverse the array
		for (int i = 0, times = length / 2; i < times; i++) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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
		if (Validates.isEmpty(array)) {
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

		if (!Validates.isEmpty(lists)) {
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

	private Arrays2() {
	}

}
