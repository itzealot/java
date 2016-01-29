package com.zt.util;

public class FormatPattern {

	private static String FORMAT_STRING_DEFAULT = "";

	public FormatPattern() {

	}

	/**
	 * To append offset counts pattern behind the String source
	 * 
	 * @param source
	 *            the String source
	 * @param offset
	 *            the Integer offset
	 * @param pattern
	 *            the String pattern to append
	 * @return
	 */
	private static String append(String source, Integer offset, String pattern) {
		StringBuffer buffer = new StringBuffer(source);
		if (offset == null) {
			return buffer.toString();
		}
		// generate n counts 0
		for (int i = 1; i <= offset; i++) {
			buffer.append(pattern);
		}
		return buffer.toString();
	}

	/**
	 * To get the pattern by patternCode, offset, and format.<br />
	 * 0 that means append 0<br />
	 * 1 that means append #<br />
	 * 2 that means format telephone number<br />
	 * 
	 * @param patternCode
	 *            the pattern code
	 * @param offset
	 *            number counts behind the char '.'
	 * @param format
	 *            the format flag
	 * @return
	 */
	public static String getPattern(int patternCode, Integer offset,
			Boolean format) {
		switch (patternCode) {
		case 0:
			return getStringPatternZero(offset, format);
		case 1:
			return getStringPattern(offset, format);
		case 2:
			return getNumberStringPattern();
		}
		return FORMAT_STRING_DEFAULT;
	}

	/**
	 * It consists of '0', '.', '.'<br />
	 * To format by String '0,000.0...0' and append the char '0' offset counts
	 * behind the char '.'.<br />
	 * 返回offset个0组成的串，使用"0,000"格式化数字.<br />
	 * 
	 * @param offset
	 *            number counts behind the char '.'
	 * @return
	 */
	public static String getStringPatternZero(int offset) {
		if (offset <= 0) {
			return FORMAT_STRING_DEFAULT;
		}
		return append("0,000.", offset, "0");
	}

	/**
	 * It consists of '0', '.', '.'<br />
	 * To format by String '0,000.0...0' and append the char '0' offset counts
	 * behind the char '.'.<br />
	 * 返回offset个0组成的串，根据flag是否使用"0,000"格式化数字.<br />
	 * 
	 * @param offset
	 *            number counts behind the char '.'
	 * @param flag
	 *            the format flag
	 * @return
	 */
	public static String getStringPatternZero(int offset, boolean flag) {
		if (offset <= 0) {
			return FORMAT_STRING_DEFAULT;
		}
		String source = "0.";
		if (flag) {
			source = "0,000.";
		}
		return append(source, offset, "0");
	}

	/**
	 * It consists of '#', '.', '.'<br />
	 * To format by String '#,###.#...#' and append the char '#' offset counts
	 * behind the char '.'.<br />
	 * 返回offset个#组成的串，使用"#,###"格式化数字.<br />
	 * 
	 * @param offset
	 *            number counts behind the char '.'
	 * @return
	 */
	public static String getStringPattern(int offset) {
		if (offset <= 0) {
			return FORMAT_STRING_DEFAULT;
		}
		return append("#,###.", offset, "#");
	}

	/**
	 * It consists of '#', '.', '.'.<br />
	 * To format by String '#,###.#...#'.<br />
	 * If the flag is true that means format by String '#,###'; else not format
	 * by String "#.".<br />
	 * 返回offset个#组成的串，根据flag是否使用"#,###"格式化数字.<br />
	 * 
	 * @param offset
	 *            number counts behind the char '.'
	 * @param flag
	 *            the format flag
	 * @return
	 */
	public static String getStringPattern(int offset, boolean flag) {
		if (offset <= 0) {
			return FORMAT_STRING_DEFAULT;
		}
		String source = "#.";
		if (flag) {
			source = "#,###.";
		}
		return append(source, offset, "#");
	}

	/**
	 * To format telephone number String by "#,####".<br />
	 * 返回格式化电话号码的串"#,####"
	 * 
	 * @return
	 */
	public static String getNumberStringPattern() {
		return "#,####";
	}

	/**
	 * To format the String by "0...0" and there are offset counts 0; <br />
	 * 返回由offset个0组成的串(0...0)，输出时数字不够用0填充<br />
	 * 
	 * @param offset
	 *            the offset counts 0
	 * @return
	 */
	public static String getZeropattern(int offset) {
		return append("", offset, "0");
	}
}
