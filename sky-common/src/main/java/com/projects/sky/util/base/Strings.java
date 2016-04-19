package com.projects.sky.util.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Strings {

	public static String trim(String str) {
		if (Validates.validate(str)) {
			return str;
		}

		return str.trim();
	}

	public static String trimNull(String str) {
		if (str == null) {
			return "";
		}

		return str;
	}

	public static String append(final String str, final String prefix, final String suffix) {
		String newStr = str;

		if (prefix != null) {
			newStr = prefix + str;
		}

		if (suffix != null) {
			newStr = newStr + suffix;
		}

		return newStr;
	}

	public static String[] split(String str, String regex) {
		if (Validates.validate(str)) {
			return str.split(regex);
		}

		return null;
	}

	public static String nameOf(Class<?> clazz) {
		int index = clazz.toString().lastIndexOf(".");
		return clazz.toString().substring(index + 1);
	}

	/**
	 * class name
	 * 
	 * @since for JDK 1.6
	 * @param clazz
	 * @return
	 */
	public static String simpleName(Class<?> clazz) {
		return clazz.getSimpleName();
	}

	public static String hqlOf(Class<?> clazz) {
		String className = simpleName(clazz);
		return "FROM " + className + " as t";
	}

	public static String hqlIdOf(Class<?> clazz) {
		String className = simpleName(clazz);
		return "SELECT t.id FROM " + className + " as t";
	}

	public static String hqlCountOf(Class<?> clazz) {
		String className = simpleName(clazz);
		return "SELECT COUNT(t.id) FROM " + className + " as t";
	}

	/**
	 * 半角DBC(占一个字节)转全角SBC(占两个字节)
	 * 
	 * @param source
	 * @return
	 */
	public static String toSBC(String source) {
		if (source == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char ch = source.charAt(i);

			if (ch == ' ') { // 空格特殊处理
				buffer.append('\u3000');
			} else if (ch < '\177') {
				buffer.append((char) (ch + 65248));
			}
		}

		return buffer.toString();
	}

	/**
	 * 全角SBC(占两个字节)转半角DBC(占一个字节)
	 * 
	 * @param source
	 * @return
	 */
	public static String toDBC(String source) {
		if (source == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		int len = source.length();

		for (int i = 0; i < len; i++) {
			char ch = source.charAt(i);

			if (ch == '\u3000') { // 空格特殊处理
				buffer.append(' ');
			} else if (ch > '\uFF00' && ch < '\uFF5F') {
				buffer.append((char) (ch - 65248));
			}
		}

		return buffer.toString();
	}

	/**
	 * 简单的分割串实现字符串的快速分割，也可以使用StringTokenizer 来分割
	 * 
	 * @param source
	 * @param split
	 * @return
	 */
	public static List<String> split(String source, char split) {
		return split(source, split, false);
	}

	/**
	 * 简单的分割串实现字符串的快速分割，也可以使用StringTokenizer 来分割.<br />
	 * 此处使用String类中的indexOf(char ch)方法查找分割字符的位置.<br />
	 * 
	 * @param source
	 * @param split
	 * @param flag
	 * @return
	 */
	public static List<String> split(String source, char split, boolean flag) {
		if (source == null || "".equals(source.trim())) {
			return null;
		}
		final String splitFinalStr = "" + split;
		String temp = source;
		List<String> strings = new ArrayList<String>();
		while (true) {
			String splitStr = null;

			/**
			 * indexOf 即在字符串中查找 split字符的位置并返回，找到返回index；否则返回-1.
			 */
			int j = temp.indexOf(split);

			// not find
			if (j == -1) {
				// add into List
				strings.add(temp);
				break;
			}
			// To get the substring
			splitStr = temp.substring(0, j);

			// add into List
			strings.add(splitStr);

			if (flag) {
				strings.add(splitFinalStr);
			}

			// To change the temp String
			temp = temp.substring(j + 1);
		}
		return strings;
	}

	/**
	 * 简单的分割串实现字符串的快速分割，也可以使用StringTokenizer 来分割.<br />
	 * 此处使用String类中的indexOf(String split)方法查找分割字符的位置.<br />
	 * 
	 * @param source
	 * @param split
	 *            the String split
	 * @param flag
	 * @return
	 */
	public static List<String> split(String source, String split, boolean flag) {
		if (source == null || "".equals(source.trim())) {
			return null;
		}
		String temp = source;
		List<String> strings = new ArrayList<String>();
		while (true) {
			String splitStr = null;

			/**
			 * indexOf 即在字符串中查找 split字符的位置并返回，找到返回index；否则返回-1.
			 */
			int j = temp.indexOf(split);

			// not find
			if (j == -1) {
				// add into List
				strings.add(temp);
				break;
			}
			// To get the substring
			splitStr = temp.substring(0, j);

			// add into List
			strings.add(splitStr);

			if (flag) {
				strings.add(split);
			}

			// To change the temp String
			temp = temp.substring(j + 1);
		}
		return strings;
	}

	public static boolean startsWith(String source, String prefix) {
		checkNotNull(source, "source String can not be null");
		checkNotNull(prefix, "prefix String can not be null");

		return source.startsWith(prefix);
	}

	public static boolean endsWith(String source, String suffix) {
		checkNotNull(source, "source String can not be null");
		checkNotNull(suffix, "suffix String can not be null");

		return source.endsWith(suffix);
	}

	public static String trimWith0(String src) {
		if (src == null) {
			return "0";
		}

		String s = src.trim();

		return s == "" ? "0" : s;
	}

	public static Object numberOf(String src, Class<?> clazz) {
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
			e.printStackTrace();
		}

		return null;
	}

	public static <T> List<T> numbersOf(String[] idStr, Class<?> clazz) {
		if (!Validates.validate(idStr)) {
			return null;
		}

		int length = idStr.length;

		List<T> lists = new ArrayList<>();

		for (int i = 0; i < length; i++) {

			@SuppressWarnings("unchecked")
			T t = (T) numberOf(idStr[i], clazz);
			lists.add(t);
		}

		return lists;
	}

	/**
	 * 原串 source 是否包含 目标串 dest，支持特殊字符匹配.<br />
	 * --1). * is all.<br />
	 * --2). _ is one char.<br />
	 * --3). # is one number.<br />
	 * --4). $ is one word.<br />
	 * 
	 * @param source
	 *            String
	 * @param dest
	 * @return
	 */
	public boolean contains(String source, String dest, String special) {
		int index1 = source.indexOf(special);

		// 包含特殊字符
		if (index1 != -1) {
			while (true) {
				source.substring(0, index1);
				source.substring(index1 + 1);
			}
		}

		return source.contains(dest);
	}

	public static String nameOfYear(String prefix, int offset) {
		checkNotNull(prefix, "prefix must not be null");

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		year += offset;

		return prefix + "_" + year;
	}

	public static String nameOfYearAndMonth(String prefix, int offset) {
		checkNotNull(prefix, "prefix must not be null");

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0~11 -> 1~12
		int month = c.get(Calendar.MONTH) + 1;

		month += offset;

		if (month > 12) {
			year++;
			month = month - 12;
		}

		if (month < 1) {
			year--;
			month = month + 12;
		}

		DecimalFormat format = new DecimalFormat();

		// 使用格式即两位，不够高位补0. To format the number
		format.applyPattern("00");

		return prefix + "_" + year + "_" + format.format(month);
	}

	public static String md5(String source) throws Exception {
		if (source == null || "".equals(source)) {
			return source;
		}

		StringBuffer buffer = new StringBuffer();
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] bytes = source.getBytes();

		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] targ = digest.digest(bytes);

		for (byte b : targ) {
			// To get the 4 high bits
			buffer.append(chars[b >> 4 & 0x0F]);
			// To get the 4 low bits
			buffer.append(chars[b & 0x0F]);
		}

		return buffer.toString();
	}

	/***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param source
	 *            待加密字符串
	 * @return 返回40位SHA码
	 */
	public static String shaEncode(String source) throws Exception {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		// 根据UTF-8返回String source的字节码
		byte[] byteArray = source.getBytes("UTF-8");

		// 字节码的hash值
		byte[] md5Bytes = digest.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		int length = md5Bytes.length;

		for (int i = 0; i < length; i++) {
			// 获取低16位值
			int val = ((int) md5Bytes[i]) & 0xFF;

			// 值小于16，补0
			if (val < 16) {
				hexValue.append("0");
			}

			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	public static int indexOf(String strs[], String str) {
		if (strs == null || strs.length == 0 || str == null || str == "") {
			return -1;
		}

		int len = strs.length;
		for (int i = 0; i < len; i++) {
			if (str.equals(strs[i])) {
				return i;
			}
		}

		return -1;
	}

	public static boolean contains(String strs[], String str) {
		return indexOf(strs, str) != -1;
	}
}
