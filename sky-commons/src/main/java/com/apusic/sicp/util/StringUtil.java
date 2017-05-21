package com.apusic.sicp.util;

import java.util.List;

public final class StringUtil {

	public static String[] append(String strs[], String str) {
		if (strs == null || strs.length == 0) {
			return strs;
		}

		if (str == null || str == "") {
			return strs;
		}

		String result[] = new String[strs.length + 1];
		for (int i = 0; i < strs.length; i++) {
			result[i] = strs[i];
		}
		result[strs.length] = str;

		strs = null;

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

	private StringUtil() {
	}

}
