package com.zt.test.util;

import java.util.Collection;
import java.util.List;

/**
 * 
 * 
 * @author zt
 *
 */
public final class CodeFormatterUtil {
	private CodeFormatterUtil() {
	}

	/**
	 * To get String from orgCode from 0 to the last index of split
	 * 
	 * @param orgCode
	 * @param split
	 * @return
	 */
	private static String from(String orgCode, String split) {
		check(orgCode);
		int index = orgCode.lastIndexOf(split);
		if (index == -1) {
			return orgCode;
		}
		return orgCode.substring(0, index);
	}

	/**
	 * 根据 List<Integer> 提供的参数格式化 string
	 * 
	 * @param orgCode
	 * @param pad
	 * @param integers
	 * @return
	 */
	public static String padIn(String orgCode, String pad, List<Integer> integers) {
		String formatter = from(orgCode, "-");
		check(integers);
		check(pad);

		StringBuffer buffer = new StringBuffer();
		int j = 0;
		int lenJ = integers.size();
		int length = formatter.length();
		int i = 0;
		for (; i < length && j < lenJ; i++) {
			buffer.append(formatter.charAt(i));
			if (i == integers.get(j)) {
				buffer.append(pad);
				j++;
			}
		}
		for (; i < length; i++) {
			buffer.append(formatter.charAt(i));
		}
		return buffer.toString();
	}

	private static void check(String string) {
		if (!validate(string)) {
			throw new ValidateException("'" + string + "' is not validate, it's null or empty String after trim.");
		}
	}

	private static boolean validate(String string) {
		if (string == null || "".equals(string.trim())) {
			return false;
		}
		return true;
	}

	private static void check(Collection<?> collection) {
		if (!validate(collection)) {
			throw new ValidateException(collection + " is not validate, it's null or empty.");
		}
	}

	private static boolean validate(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return false;
		}
		return true;
	}
}
