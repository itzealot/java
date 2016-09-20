package com.sky.projects.util.base;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Base64 Util
 * 
 * @author zealot
 * @since 1.8
 */
public final class Base64s {

	/**
	 * decode by {@link java.nio.charset.StandardCharsets}
	 * 
	 * @param encode
	 * @param ch
	 * 
	 * @return
	 */
	public static String decode(String decode, Charset ch) {
		return new String(decode(decode.getBytes(ch)));
	}

	public static String decode(String decode) {
		return new String(decode(decode.getBytes()));
	}

	public static byte[] decode(byte[] bytes) {
		return Base64.getDecoder().decode(bytes);
	}

	public static byte[] encode(byte[] bytes) {
		return Base64.getEncoder().encode(bytes);
	}

	public static String encode(String encode) {
		return new String(encode(encode.getBytes()));
	}

	/**
	 * encode by {@link java.nio.charset.StandardCharsets}
	 * 
	 * @param encode
	 * @param ch
	 * 
	 * @return
	 */
	public static String encode(String encode, Charset ch) {
		return new String(encode(encode.getBytes(ch)));
	}

	private Base64s() {
	}
}
