package com.sky.projects.util.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * String Util
 * 
 * @author zt
 */
public final class Strings {

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	public static String trimNull(String str) {
		return str == null ? "" : str;
	}

	public String join(final String... strings) {
		return strings == null ? null : join0(strings);
	}

	String join0(final String... strings) {
		StringBuffer buffer = new StringBuffer();

		for (String str : strings) {
			buffer.append(str);
		}

		return buffer.toString();
	}

	public static String[] split(String str, String regex) {
		return str == null ? null : str.split(regex);
	}

	/**
	 * Class simple name
	 * 
	 * @param clazz
	 * @return
	 */
	public static String nameOf(Class<?> clazz) {
		String name = clazz.toString();
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * Class simple name
	 * 
	 * @since for JDK 1.6
	 * @param clazz
	 * @return
	 */
	public static String simpleName(Class<?> clazz) {
		return clazz.getSimpleName();
	}

	/**
	 * 半角DBC(占一个字节)转全角SBC(占两个字节)
	 * 
	 * @param source
	 * @return
	 */
	public static String toSBC(String source) {
		return source == null ? null : toSBC0(source);
	}

	/**
	 * 半角DBC(占一个字节)转全角SBC(占两个字节)
	 * 
	 * @param source
	 * @return
	 */
	static String toSBC0(String source) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = source.length(); i < len; i++) {
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
		return source == null ? null : toDBC0(source);
	}

	/**
	 * 全角SBC(占两个字节)转半角DBC(占一个字节)
	 * 
	 * @param source
	 * @return
	 */
	static String toDBC0(String source) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0, len = source.length(); i < len; i++) {
			char ch = source.charAt(i);

			if (ch == '\u3000') { // 空格特殊处理
				buffer.append(' ');
			} else if (ch > '\uFF00' && ch < '\uFF5F') {
				buffer.append((char) (ch - 65248));
			}
		}

		return buffer.toString();
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
		return src == null ? "0" : (src.trim() == "" ? "0" : src.trim());
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

	@SuppressWarnings("unchecked")
	public static <T> List<T> numbersOf(String[] idStr, Class<?> clazz) {
		List<T> lists = new ArrayList<>();

		for (int i = 0, len = idStr.length; i < len; i++) {
			lists.add((T) numberOf(idStr[i], clazz));
		}

		return lists;
	}

	public static String md5(String source) {
		return Validates.isEmpty(source) ? source : md5_(source);
	}

	private static String md5_(String source) {
		MessageDigest digest = null;
		StringBuffer buffer = new StringBuffer();
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] targ = digest.digest(source.getBytes());
			for (byte b : targ) {
				buffer.append(chars[b >> 4 & 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
		} catch (NoSuchAlgorithmException e) {
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
	public static String shaEncode(String source) {
		MessageDigest digest = null;

		try {
			digest = MessageDigest.getInstance("SHA");
			// 字节码的hash值
			byte[] md5Bytes = digest.digest(source.getBytes("UTF-8"));
			StringBuffer hexValue = new StringBuffer();

			for (int i = 0, length = md5Bytes.length; i < length; i++) {
				// 获取低16位值
				int val = ((int) md5Bytes[i]) & 0xFF;

				if (val < 16) { // 值小于16，补0
					hexValue.append("0");
				}

				hexValue.append(Integer.toHexString(val));
			}

			return hexValue.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static int indexOf(String[] strs, String str) {
		return Validates.isEmpty(strs) || Validates.isEmpty(str) ? -1 : indexOf0(strs, str);
	}

	static int indexOf0(String strs[], String str) {
		for (int i = 0, len = strs.length; i < len; i++) {
			if (str.equals(strs[i])) {
				return i;
			}
		}

		return -1;
	}

	public static boolean contains(String strs[], String str) {
		return indexOf(strs, str) != -1;
	}

	private Strings() {
	}
}
