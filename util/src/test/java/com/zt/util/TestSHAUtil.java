package com.zt.util;

import org.junit.Test;

public class TestSHAUtil {

	@Test
	public void testShaEncode() throws Exception {
		System.out.println(SHAUtil.shaEncode("aAAA"));
		System.out.println(SHAUtil.shaEncode("aAAA"));
		System.out.println(SHAUtil.shaEncode("bbb"));
	}
}
