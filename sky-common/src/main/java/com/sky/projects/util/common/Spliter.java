package com.sky.projects.util.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class Spliter {

	/**
	 * 解析不规则的数据，适合如果值为空，则使用 delim 代替的情况
	 * 
	 * @param value
	 * @param delim
	 * @param defaultValue
	 * @param filedSize
	 * @return
	 */
	public static List<String> parse(String value, String delim, String defaultValue, int filedSize) {
		if (value.indexOf(delim) == 0) {// 处理第一个为空的情况
			value = defaultValue + delim + value.substring(1);
		}

		List<String> reults = append(new StringTokenizer(replace(value, delim, defaultValue), delim));

		int len = reults.size();
		if (len > filedSize) {
			reults.clear();
			return reults;
		}

		for (int i = len; i < filedSize; i++) {
			reults.add(defaultValue);
		}

		return reults;
	}

	/**
	 * 使用分隔符 delim 解析规则的一行数据，适合字段都是有值的
	 * 
	 * @param line
	 * @param delim
	 * @return
	 */
	public static List<String> parse(String line, String delim) {
		return append(new StringTokenizer(line, delim));
	}

	/**
	 * 使用分隔符 delim 解析规则的一行数据，适合数据
	 * 
	 * @param line
	 * @param delim
	 * @return
	 */
	public static void parse(String line, String delim, List<String> results) {
		append(new StringTokenizer(line, delim), results);
	}

	public static String[] split(String line, String regex) {
		return line == null ? null : line.split(regex);
	}

	/**
	 * 替换值，即三个连续分隔符则包含一个
	 * 
	 * @param source
	 * @param delim
	 * @param value
	 * @return
	 */
	private static String replace(final String source, String delim, String value) {
		String oldString = delim + delim + delim;
		String newString = delim + value;

		StringBuffer buffer = new StringBuffer();
		int index = 0;
		String current = source;

		while ((index = current.indexOf(oldString)) != -1) {
			buffer.append(current.substring(0, index));
			buffer.append(newString);
			current = current.substring(index + delim.length() * 2);
		}

		return buffer.append(current).toString();
	}

	private static List<String> append(StringTokenizer tokenizer) {
		List<String> results = new ArrayList<>();

		while (tokenizer.hasMoreTokens()) {
			results.add(tokenizer.nextToken());
		}

		return results;
	}

	private static void append(StringTokenizer tokenizer, List<String> results) {
		while (tokenizer.hasMoreTokens()) {
			results.add(tokenizer.nextToken());
		}
	}

	private Spliter() {
	}
}
