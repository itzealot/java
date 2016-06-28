package com.surfilter.mass;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ParseLine2Object {

	public static void main(String[] args) {
		testTrim();

		int size = 5;
		testTrim(size);
	}

	public static void testTrim() {
		final String delim = "\t";
		trim("AAAAAAAA	BBBB	CCCCCCCCCCC	DDDDDDDDDDDDDD	EEEEEE", delim);
	}

	public static void testTrim(int size) {
		final String defaultValue = "MULL";
		final String delim = "\t";
		trim("						DDDDDDDDDDDDDD	EEEEEE", delim, defaultValue, size);
		trim("	BBBB			DDDDDDDDDDDDDD	EEEEEE", delim, defaultValue, size);
		trim("AAAAAAAA					DDDDDDDDDDDDDD	EEEEEE", delim, defaultValue, size);
		trim("AAAAAAAA	BBBB			DDDDDDDDDDDDDD	EEEEEE", delim, defaultValue, size);
		trim("AAAAAAAA							EEEEEE", delim, defaultValue, size);
		trim("AAAAAAAA	MULL	CCCCCCCCCCC	MULL	MULL", delim, defaultValue, size);
		trim("AAAAAAAA	BBBB			DDDDDDDDDDDDDD	EEEEEE", delim, defaultValue, size);
		trim("AAAAAAAA	BBBB	CCCCCCCCCCC				", delim, defaultValue, size);
		trim("AAAAAAAA	BBBB			DDDDDDDDDDDDDD		", delim, defaultValue, size);
	}

	public static void trim(String value, String delim, String defaultValue, int filedSize) {
		// 处理第一个为空的情况
		if (value.indexOf(delim) == 0) {
			value = defaultValue + delim + value.substring(1);
		}

		String res = replace(value, delim, defaultValue);

		StringTokenizer tokenizer = new StringTokenizer(res, delim);

		List<String> reults = append(tokenizer);
		for (int i = reults.size(); i < filedSize; i++) {
			reults.add(defaultValue);
		}

		System.out.println(reults);
	}

	public static void trim(String value, String delim) {
		StringTokenizer tokenizer = new StringTokenizer(value, delim);
		System.out.println(append(tokenizer));
	}

	public static String replace(final String source, String delim, String value) {
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

	public static List<String> append(StringTokenizer tokenizer) {
		List<String> results = new ArrayList<>();

		while (tokenizer.hasMoreTokens()) {
			results.add(tokenizer.nextToken());
		}

		return results;
	}
}
