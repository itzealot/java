package com.sky.projects.jdk.lambda;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.Test;

/***
 * Base64编码已经成为Java类库的标准。它的使用十分简单.<br />
 * 
 * Base64类同时还提供了对URL、MIME友好的编码器与解码器（Base64.getUrlEncoder() /
 * Base64.getUrlDecoder(), Base64.getMimeEncoder() / Base64.getMimeDecoder()）
 * 
 * @author zt
 *
 */
public class Base64s {
	/**
	 * Base64类同时还提供了对URL、MIME友好的编码器与解码器（Base64.getUrlEncoder() /
	 * Base64.getUrlDecoder(), Base64.getMimeEncoder() /
	 * Base64.getMimeDecoder()）
	 */
	@Test
	public void test() {
		final String text = "Base64 finally in Java 8!";
		final String encoded = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(encoded);

		final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
		System.out.println(decoded);
	}
}