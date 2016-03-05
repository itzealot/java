package com.sky.projects.sky.util;

import org.junit.Test;

import com.zt.projects.sky.util.SHAUtil;

public class TestSHAUtil {

	@Test
	public void testShaEncode() throws Exception {
		System.out.println(SHAUtil.shaEncode("aAAA"));
		System.out.println(SHAUtil.shaEncode("aAAA"));
		System.out.println(SHAUtil.shaEncode("bbb"));
	}
}
