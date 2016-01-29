package com.zt.util;

import com.zt.util.ValidateUtil;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class String2NumberUtil {

	/**
	 * To deal with the String before transfer to number.<br />
	 * If the String src is null or is equals empty String when was trim, then
	 * return String "0"; else return the String src.trim
	 * 
	 * @param src
	 * @return
	 */
	public static String dealString(String src) {
		if (src == null) {
			return "0";
		}
		String s = src.trim();
		return s == "" ? "0" : s;
	}

	/**
	 * To get the number from String src. If change success then return the
	 * transfer number; else return null.<br />
	 * 
	 * @param src
	 * @return
	 */
	public static Object getNumberFromString(String src, Class<?> clazz) {
		try {
			if (clazz == Byte.class) {
				return Byte.parseByte(src);
			} else if (clazz == Short.class) {
				return Short.parseShort(src);
			} else if (clazz == Integer.class) {
				return Integer.parseInt(src);
			} else if (clazz == Float.class) {
				return Float.parseFloat(src);
			} else if (clazz == Long.class) {
				return Long.parseLong(src);
			} else if (clazz == Double.class) {
				return Double.parseDouble(src);
			}
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 将字符串数组转为数字数组
	 * 
	 * @param roelIds
	 * @return
	 */
	public static Object[] getIntIdsFromStringIds(String[] idStr, Class<?> clazz) {
		// TODO Auto-generated method stub
		if (!ValidateUtil.isValidate(idStr)) {
			return null;
		}
		int length = idStr.length;
		Object[] ids = new Object[length];
		for (int i = 0; i < length; i++) {
			ids[i] = getNumberFromString(idStr[i], clazz);
		}
		return ids;
	}

}
