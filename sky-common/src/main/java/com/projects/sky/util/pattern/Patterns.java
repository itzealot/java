package com.projects.sky.util.pattern;

import java.util.regex.Pattern;

/**
 * The Pattern Util
 * 
 * @author zealot
 *
 */
public final class Patterns {

	/**
	 * the number regex pattern to match
	 */
	public static final Pattern NUMBER_REGEX = Pattern.compile("^\\d+$");

	/**
	 * the float number regex pattern to match
	 */
	public static final Pattern FLOAT_REGEX = Pattern.compile("^(\\d*\\.)?\\d+$");

	/**
	 * the integer number regex pattern to match
	 */
	public static final Pattern INTEGER_REGEX = Pattern.compile("^-?[0-9]\\d*$");

	/**
	 * the mac adress regex pattern to match
	 */
	public static final Pattern MAC_REGEX = Pattern.compile("([0-9A-F]{2}-){5}[0-9A-F]{2}");

	/**
	 * the Chinese regex pattern to find
	 */
	public static final Pattern CHINESE_REGEX_FIND = Pattern.compile("[\u4e00-\u9fa5]");

	/**
	 * the Chinese regex pattern to match
	 */
	public static final Pattern CHINESE_REGEX = Pattern.compile("([\u4e00-\u9fa5]+)");

	/**
	 * the mobile phone regex pattern to match
	 */
	public static final Pattern MOBILE_REGEX = Pattern.compile("^(0|86|17951|0086)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$");

	public static boolean matches(Pattern pattern, CharSequence input) {
		return pattern.matcher(input).matches();
	}

	public static boolean find(Pattern pattern, CharSequence input) {
		return pattern.matcher(input).find();
	}

	public static boolean findChinese(String source) {
		return CHINESE_REGEX_FIND.matcher(source).find();
	}

	public static boolean matchChinese(String source) {
		return CHINESE_REGEX.matcher(source).matches();
	}

	private Patterns() {
	}
}
