package com.sky.projects.util.base;

import java.util.Collection;
import java.util.Map;

/**
 * Validate Util
 * 
 * @author zt
 */
public final class Validates {

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(boolean[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(byte[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(short[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean vlidate(char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(float[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(long[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when array is null or length is 0 then return true; else return false
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static <E> boolean isEmpty(E[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * when object is null then return true; else return false.
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * when str is null length is 0 then return true; else return false.
	 * 
	 * 数组为null或长度为0时返回true，否则返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * when Collection is null or length is 0 then return true; else return
	 * false.
	 * 
	 * Collection 为null或长度为0时返回true，否则返回false
	 * 
	 * @param collection
	 * @return
	 */
	public static <E> boolean isEmpty(Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * when Map is null or length is 0 then return true; else return false.
	 * 
	 * Map 为null或长度为0时返回true，否则返回false
	 * 
	 * @param collection
	 * @return
	 */
	public static <E, V> boolean isEmpty(Map<E, V> map) {
		return map == null || map.isEmpty();
	}

	private Validates() {
	}
}
