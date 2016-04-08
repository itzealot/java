package com.projects.sky.util.base;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Strings {

	public static String trim(String str) {
		if (str == null || str == "") {
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

	public static String append(String str, String start, String end) {
		String newStr = str;

		if (start != null) {
			newStr = start + str;
		}

		if (end != null) {
			newStr = newStr + end;
		}

		return newStr;
	}

	/**
	 * 对指定的字符串(str)按照给定的拆分方法(tag)进行拆分
	 * 
	 * @param str
	 * @param tag
	 * @return 返回拆分后的字符串数组
	 */
	public static String[] str2StrArray(String str, String tag) {
		if (Validates.validate(str)) {
			return str.split(tag);
		}

		return null;
	}

	/**
	 * JDK for all.<br />
	 * To get the Class name.<br />
	 * 从Class<T>中获取class name.<br />
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> String getClassName(Class<? extends T> clazz) {
		int index = clazz.toString().lastIndexOf(".");
		return clazz.toString().substring(index + 1);
	}

	/**
	 * JDK for above 1.5<br />
	 * To get the Class's simple name.<br />
	 * 从Class<T>中获取class name.<br />
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> String getClassSimpleName(Class<? extends T> clazz) {
		return clazz.getSimpleName();
	}

	/**
	 * To get hql from Class to select the Object.<br />
	 * 从Class<T>中获取Hql语句<br />
	 * The result like SELECT t FROM table as t.<br />
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String getHqlFromClass(Class<? extends T> clazz) {
		String className = getClassName(clazz);
		return "FROM " + className + " as t";
	}

	/**
	 * To get hql from Class to select the Object.<br />
	 * 从Class<T>中获取Hql语句.<br />
	 * The result like SELECT t.id FROM table as t.<br />
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String getIdHqlFromClass(Class<? extends T> clazz) {
		String className = getClassName(clazz);
		return "SELECT t.id FROM " + className + " as t";
	}

	/**
	 * To get Hql from Class to count the entity by id.<br />
	 * 从Class<T>中获取Hql语句.<br />
	 * The result like SELECT COUNT(t.id) FROM table as t.<br />
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String getHqlCountFromClass(Class<? extends T> clazz) {
		String className = getClassName(clazz);
		return "SELECT COUNT(t.id) FROM " + className + " as t";
	}

	/**
	 * 半角DBC(占一个字节)转全角SBC(占两个字节)
	 * 
	 * @param input
	 *            the String input
	 * @return the SBC String
	 */
	public static String toSBC(String input) {

		// 将字符串转换为字符数组
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// 空格特殊处理
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 全角SBC(占两个字节)转半角DBC(占一个字节)
	 * 
	 * @param input
	 *            the String input
	 * @return the DBC String
	 */
	public static String toDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			// 空格特殊处理
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * 简单的分割串实现字符串的快速分割，也可以使用StringTokenizer 来分割.<br />
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

	/**
	 * 判断源字符串source 是否以start 字符串开头，是则返回true；否则返回false
	 * 
	 * @param source
	 * @param start
	 * @return
	 */
	public static boolean startWith(String source, String start) {
		if (source == null) {
			throw new NullPointerException(source);
		}

		if (start == null) {
			throw new NullPointerException(start);
		}

		// The start String's length
		int lengthStart = start.length();

		// The source String's length
		int lengthSource = source.length();

		// 源串source 长度小于开始串start 长度
		if (lengthSource < lengthStart) {
			return false;
		} else if (lengthSource == lengthStart) {
			return source.equals(start);
		} else {
			for (int i = 0; i < lengthStart; i++) {
				if (source.charAt(i) != start.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断源字符串source 是否以end 字符串结尾，是则返回true；否则返回false
	 * 
	 * @param source
	 * @param start
	 * @return
	 */
	public static boolean endWith(String source, String end) {
		if (source == null) {
			throw new NullPointerException(source);
		}

		if (end == null) {
			throw new NullPointerException(end);
		}

		// The start String's length
		int lengthEnd = end.length();

		// The source String's length
		int lengthSource = source.length();

		// 源串source 长度小于开始串start 长度
		if (lengthSource < lengthEnd) {
			return false;
		} else if (lengthSource == lengthEnd) {
			return source.equals(end);
		} else {
			int absLength = lengthSource - lengthEnd;
			for (int i = 0; i < lengthEnd; i++) {
				if (end.charAt(i) != source.charAt(absLength + i)) {
					return false;
				}
			}
		}
		return true;
	}

	public static String dealString(String src) {
		if (src == null) {
			return "0";
		}

		String s = src.trim();

		return s == "" ? "0" : s;
	}

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
			e.printStackTrace();
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
		if (!Validates.validate(idStr)) {
			return null;
		}
		int length = idStr.length;
		Object[] ids = new Object[length];
		for (int i = 0; i < length; i++) {
			ids[i] = getNumberFromString(idStr[i], clazz);
		}
		return ids;
	}

	/**
	 * the source String is containing the destination String.<br />
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

	/**
	 * 生成日志表名称:logs_year_month.
	 * 
	 * @param offset
	 *            the value about the current month
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		// the singleton Calendar
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0-11 + 1 = 1 - 12
		int month = c.get(Calendar.MONTH) + 1;

		// 加偏移量
		month += offset;
		if (month > 12) {
			year++;
			// or month -= 12
			month = month - 12;
		}
		if (month < 1) {
			year--;
			month = month + 12;
		}
		DecimalFormat format = new DecimalFormat();
		// 使用格式即两位，不够高位补0
		format.applyPattern("00");
		return "logs_" + year + "_" + format.format(month);
	}

	/**
	 * 根据tableName与offset生成表名称.
	 * 
	 * @param tableName
	 *            the tableName you want you generate
	 * @param offset
	 *            the offset the input
	 * @return
	 */
	public static String getTableName(String tableName, int offset) {
		// the singleton Calendar
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0-11 + 1 = 1 - 12
		int month = c.get(Calendar.MONTH) + 1;

		// 加偏移量.To add offset
		month += offset;

		if (month > 12) {
			year++;
			// or month -= 12
			month = month - 12;
		}

		if (month < 1) {
			year--;
			month = month + 12;
		}

		DecimalFormat format = new DecimalFormat();

		// 使用格式即两位，不够高位补0. To format the number
		format.applyPattern("00");

		return tableName + "_" + year + "_" + format.format(month);
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
}
