package com.zt.util;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class Array2StringUtil {

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the boolean[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(boolean[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the byte[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(byte[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the short[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(short[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the char[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(char[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the int[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(int[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the float[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(float[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the long[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(long[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the double[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static String getStringFromArray(double[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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

	/**
	 * To get the String from array and divide by the String split.<br />
	 * If the flag is true then append a line on the end and return the result<br />
	 * String; else return the result String.<br />
	 * 
	 * @param array
	 *            the double[] array
	 * @param split
	 *            the divided String split
	 * @param flag
	 *            display a line boolean flag
	 * @return
	 */
	public static <E> String getStringFromArray(E[] array, String split,
			boolean flag) {
		if (!ValidateUtil.isValidate(array)) {
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
}
