package com.projects.sky.util.base;

import org.junit.Test;

public class TestSHAUtil {

	@Test
	public void testShaEncode() throws Exception {
		System.out.println(Strings.shaEncode("aAAA"));
		System.out.println(Strings.shaEncode("aAAA"));
		System.out.println(Strings.shaEncode("bbb"));
	}
}
