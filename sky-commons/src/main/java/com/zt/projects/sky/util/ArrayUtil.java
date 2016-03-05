package com.zt.projects.sky.util;

import java.util.List;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class ArrayUtil {

	/**
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 * @return the reverse array.
	 */
	public static <E> E[] reverse(E[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the boolean[] array
	 * @return the reverse array.
	 */
	public static boolean[] reverse(boolean[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the byte[] array
	 * @return the reverse array.
	 */
	public static byte[] reverse(byte[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            short char[] array
	 * @return the reverse array.
	 */
	public static short[] reverse(short[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the char[] array
	 * @return the reverse array.
	 */
	public static char[] reverse(char[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the int[] array
	 * @return the reverse array.
	 */
	public static int[] reverse(int[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the float[] array
	 * @return the reverse array.
	 */
	public static float[] reverse(float[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the long[] array
	 * @return the reverse array.
	 */
	public static long[] reverse(long[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
	 * 
	 * @param array
	 *            the double[] array
	 * @return the reverse array.
	 */
	public static double[] reverse(double[] array) {
		if (!ValidateUtil.isValidate(array)) {
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
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			boolean temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			byte temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			short temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			char temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			int temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			float temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			long temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To reverse the array from start to end and return the reverse's array.<br />
	 * 返回反转之后的数组.<br />
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
		if (!ValidateUtil.isValidate(array)) {
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
			double temp = array[from];
			array[from] = array[to];
			array[to] = temp;
		}
		return array;
	}

	/**
	 * To get the list from List<T> where the index is between start and end,
	 * then save into newLists<T>.<br />
	 * 
	 * @param lists
	 * @param start
	 * @param length
	 * @param newLists
	 * @return
	 */
	public static <T> void getListFromList(List<T> lists, int start, int end,
			List<T> newLists) {
		// not validate
		if (!ValidateUtil.isValidate(lists)
				|| !ValidateUtil.isValidate(newLists)) {
			return;
		}

		// the from index and to index
		int from = start;
		int to = end;

		// change the index from
		if (start < 0) {
			from = 0;
		}

		// change the index to
		if (end > lists.size()) {
			to = lists.size();
		}

		// To add elements into newLists<T>
		for (int i = from; i < to; i++) {
			newLists.add(lists.get(i));
		}
	}

}
