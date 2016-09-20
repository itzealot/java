package com.sky.projects.apache.common.codec;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class Base64sTest {

	@Test
	public void testEncode() {
		// 18051215958
		String decode = "MTgwNTEyMTU5NTg=";
		String encode = Base64s.decode(decode);
		System.out.println(encode);
		Assert.assertEquals(Base64s.encode(encode), decode);
	}

	@Test
	public void testEncodeBase64() throws Exception {
		// 18051215958
		String decode = "MTgwNTEyMTU5NTg=";
		String encode = decodeBase64(decode);
		System.out.println(encode);
		Assert.assertEquals(encodeBase64(encode), decode);
	}

	/***
	 * encode Base64
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64(byte[] input) throws Exception {
		Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("encode", byte[].class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, new Object[] { input });
		return (String) retObj;
	}

	/**
	 * decode Base64
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String decodeBase64(String input) throws Exception {
		Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, input);
		return new String((byte[]) retObj);
	}

	/***
	 * encode Base64
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64(String encode) throws Exception {
		return encodeBase64(encode.getBytes());
	}
}
