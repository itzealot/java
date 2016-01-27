package com.apusic.sicp.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public final class StringUtil {
	private StringUtil() {
	}

	/**
	 * 向数组中追加
	 * 
	 * @param strs
	 * @param str
	 * @return
	 */
	public static String[] append(String strs[], String str) {
		if (StringUtils.isBlank(str)) {
			return strs;
		}
		if (strs == null || strs.length == 0) {
			return new String[] { str };
		}
		String result[] = new String[strs.length + 1];
		for (int i = 0; i < strs.length; i++) {
			result[i] = strs[i];
		}
		result[strs.length] = str;
		return result;
	}

	/**
	 * 向数组中追加数组
	 * 
	 * @param strs
	 * @param str
	 * @return
	 */
	public static String[] append(List<String> src, String dec[]) {
		if (src == null || src.isEmpty()) {
			return dec;
		}
		for (String s : src) {
			dec = append(dec, s);
		}
		return dec;
	}

	/**
	 * 从列表中去除
	 * 
	 * @param strs
	 * @param str
	 * @return
	 */
	public static String[] clean(String strs[], String str) {
		if (strs == null || strs.length == 0) {
			return new String[0];
		}
		if (StringUtils.isBlank(str)) {
			return strs;
		}
		List<String> result = new ArrayList<String>();
		for (String s : strs) {
			if (str.equals(s)) {
				continue;
			} else {
				result.add(s);
			}
		}
		strs = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			strs[i] = result.get(i);
		}
		return strs;
	}

	/**
	 * 从列表中去除,位置从1开始
	 * 
	 * @param strs
	 * @param str
	 * @return
	 */
	public static String[] cleanByIndex(String strs[], int index) {
		if (strs == null || strs.length == 0) {
			return new String[0];
		}
		String[] result = new String[strs.length - 1];
		int j = 0;
		for (int i = 0; i < strs.length; i++) {
			if (i + 1 == index) {
				continue;
			}
			result[j] = strs[i];
			j++;
		}
		return result;
	}

	/**
	 * 获得str在strs中的索引位置，位置从1开始
	 * 
	 * @param strs
	 * @param str
	 * @return
	 */
	public static int index(String strs[], String str) {
		if (strs == null || strs.length == 0 || StringUtils.isBlank(str)) {
			return -1;
		}
		int i = 1;
		for (String s : strs) {
			if (s.equals(str)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static boolean contains(String strs[], String str) {
		if (strs == null || strs.length == 0 || StringUtils.isBlank(str)) {
			return false;
		}
		boolean flag = false;
		for (String s : strs) {
			if (s.equals(str)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static String formatExceptionMessage(String content) {
		return content;
	}
}
