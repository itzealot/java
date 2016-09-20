package com.sky.projects.apache.common.codec;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64 Util
 * 
 * @author zealot
 */
public final class Base64s {

	/**
	 * decode by {@link java.nio.charset.StandardCharsets}
	 * 
	 * @param src
	 * @param ch
	 * 
	 * @return
	 */
	public static String decode(final String src, final Charset ch) {
		return new String(decode(src.getBytes(ch)));
	}

	public static String decode(final String src) {
		return new String(decode(src.getBytes()));
	}

	public static byte[] decode(final byte[] bytes) {
		return Base64.decodeBase64(bytes);
	}

	public static byte[] encode(final byte[] bytes) {
		return Base64.encodeBase64(bytes);
	}

	public static String encode(final String encode) {
		return new String(encode(encode.getBytes()));
	}

	/**
	 * encode by Charset {@link java.nio.charset.StandardCharsets}
	 * 
	 * @param encode
	 * @param ch
	 * 
	 * @return
	 */
	public static String encode(final String encode, final Charset ch) {
		return new String(encode(encode.getBytes(ch)));
	}

	private Base64s() {
	}
}
