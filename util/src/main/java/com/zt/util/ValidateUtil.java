package com.zt.util;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class ValidateUtil {

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the boolean[] array
	 * @return
	 */
	public static boolean isValidate(boolean[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the byte[] array
	 * @return
	 */
	public static boolean isValidate(byte[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the short[] array
	 * @return
	 */
	public static boolean isValidate(short[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the char[] array
	 * @return
	 */
	public static boolean isValidate(char[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the int[] array
	 * @return
	 */
	public static boolean isValidate(int[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the float[] array
	 * @return
	 */
	public static boolean isValidate(float[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the long[] array
	 * @return
	 */
	public static boolean isValidate(long[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the double[] array
	 * @return
	 */
	public static boolean isValidate(double[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the array.If is validate then return true;else return false.<br />
	 * 校验数组，如果不为空且长度不为0，则返回true；否则返回false<br />
	 * 
	 * @param array
	 *            the double[] array
	 * @return
	 */
	public static <E> boolean isValidate(E[] array) {
		if (array == null || array.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the Object. If the object is null then return false; else
	 * return true.<br />
	 * 
	 * @param object
	 *            the Object object
	 * @return
	 */
	public static boolean isValidate(Object object) {
		if (object == null) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the Collection<E> object collection.<br />
	 * If it's null or empty then return false; else return true.<br />
	 * 
	 * @param collection
	 *            the Collection<E> Object
	 * @return
	 */
	public static <E> boolean isValidate(Collection<E> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * To validate the Map<E, V> object map.<br />
	 * If it's null or empty then return false; else return true.<br />
	 * 
	 * @param map
	 *            the Map<E, V> object map
	 * @return
	 */
	public static <E, V> boolean isValidate(Map<E, V> map) {
		if (map == null || map.isEmpty()) {
			return false;
		}
		return true;
	}
}
