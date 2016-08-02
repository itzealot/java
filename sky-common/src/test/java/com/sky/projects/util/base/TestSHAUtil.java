package com.sky.projects.util.base;

import org.junit.Test;

import com.sky.projects.util.base.Strings;

public class TestSHAUtil {

	@Test
	public void testShaEncode() throws Exception {
		System.out.println(Strings.shaEncode("aAAA"));
		System.out.println(Strings.shaEncode("aAAA"));
		System.out.println(Strings.shaEncode("bbb"));
	}
}
