package com.sky.projects.book;

import com.sky.projects.book.common.SkyContext;

import junit.framework.TestCase;

public class SkyContextTest extends TestCase {

	private SkyContext skyContext = new SkyContext();

	{
		skyContext.put("aa", "aa");
		skyContext.put("bb", "bb");
		skyContext.put("cc", "cc");
		skyContext.put("1", 1);
		skyContext.put("2.0", 2.0);
	}

	public void testGet() {
		assertEquals("value is not equal", "aa", skyContext.get("aa"));
	}

	public void testPut() {
		skyContext.put("21.0", 21.0).put("21", 21);
		assertEquals("value is not equal", 21.0, skyContext.get("21.0"));
	}

}
