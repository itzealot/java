package com.sky.projects.guava;


import org.junit.Test;

import com.google.common.base.Strings;

public class TestStrings {

	/**
	 * nullToEmpty(@Nullable String string) : To convert the null String to
	 * empty String("")
	 */
	@Test
	public void test_nullToEmpty() {
		System.out.println(Strings.nullToEmpty(null));
		System.out.println(Strings.nullToEmpty("I"));
		System.out.println(Strings.nullToEmpty(" "));
		System.out.println(Strings.nullToEmpty("I "));
		System.out.println(Strings.nullToEmpty(""));
		System.out.println(Strings.nullToEmpty("I love"));
	}

	/**
	 * emptyToNull(@Nullable String string) : To convert the empty String("") to
	 * null String
	 */
	@Test
	public void test_emptyToNull() {
		System.out.println(Strings.emptyToNull(null));
		System.out.println(Strings.emptyToNull("I"));
		System.out.println(Strings.emptyToNull(" "));
		System.out.println(Strings.emptyToNull("I "));
		System.out.println(Strings.emptyToNull(""));
		System.out.println(Strings.emptyToNull("I love"));
	}

	/**
	 * isNullOrEmpty(@Nullable String string) : If the String is the empty
	 * String("") or null String then return true, else return false.
	 */
	@Test
	public void test_isNullOrEmpty() {
		System.out.println(Strings.isNullOrEmpty(null));
		System.out.println(Strings.isNullOrEmpty("I"));
		System.out.println(Strings.isNullOrEmpty(" "));
		System.out.println(Strings.isNullOrEmpty("I "));
		System.out.println(Strings.isNullOrEmpty(""));
	}

	/**
	 * padStart(String source, int minLength, char padChar) : To fill the
	 * padChar char before source String while the source String's length is
	 * less than minLength and the result String is minLength.
	 */
	@Test
	public void test_padStart() {
		System.out.println(Strings.padStart("I love you", 30, '!'));
		System.out.println(Strings.padStart("I love you", 4, '!'));
	}

	/**
	 * padEnd(String source, int minLength, char padChar) : To fill the padChar
	 * char after source String while the source String's length is less than
	 * minLength and the result String is minLength.
	 */
	@Test
	public void test_padEnd() {
		System.out.println(Strings.padEnd("I love you", 30, '!'));
		System.out.println(Strings.padEnd("I love you", 4, '!'));
	}
	
	@Test
	public void padStartWith0() {
		Integer[] vals = {1, 11, 111, 2222, 44444, 555555};
		for(int i = 0; i < vals.length; i++) {
			System.out.println(Strings.padStart(String.valueOf(vals[i]), 5, '0'));;
		}
 	}

	/**
	 * 重复字符串
	 */
	@Test
	public void test_repeat() {
		System.out.println(Strings.repeat("hey", 2));
		System.out.println(Strings.repeat("hey", 1));
		System.out.println(Strings.repeat("hey", 0));
	}

	/**
	 * Prefix : 前缀
	 */
	@Test
	public void test_commonPrefix() {
		System.out.println(Strings.commonPrefix("aaaaa", "aabsaa"));
		System.out.println(Strings.commonPrefix("aabsaaa", "aabsaa"));
		System.out.println(Strings.commonPrefix("saabsaaa", "aabsaa"));
	}

	/**
	 * Suffix : 后缀
	 */
	@Test
	public void test_commonSuffix() {
		System.out.println(Strings.commonSuffix("aaaaas", "aabsaa"));
		System.out.println(Strings.commonSuffix("aabsaaa", "aabsaa"));
		System.out.println(Strings.commonSuffix("saabsaasa", "aabsaa"));
	}
}
