package com.zt.util;

import java.util.ArrayList;
import java.util.List;

import com.zt.util.ValidateUtil;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class StringUtil {

	/**
	 * To deal with the String by trim method.<br />
	 * If the String is null or empty String then return itself; else return the
	 * String by trim method.<br />
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str == null || str == "") {
			return str;
		}
		return str.trim();
	}

	/**
	 * To trim null String.<br />
	 * If the String str is null then return empty String; else return String
	 * str
	 * 
	 * @return
	 */
	public static String trimNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * To append the String at start and end.<br />
	 * If the start String isn't null then append the start String before str;<br />
	 * If the end String isn't null then append the end String after str;
	 * 
	 * @param str
	 *            the source of String str
	 * @param start
	 *            the start String be appended before str
	 * @param end
	 *            the end String be appended behind str
	 * @return
	 */
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
		// str有效
		if (ValidateUtil.isValidate(str)) {
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
}
