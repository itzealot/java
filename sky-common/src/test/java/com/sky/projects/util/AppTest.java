package com.sky.projects.util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
		System.out.println(join("a", "B"));
	}

	public String join(final String... strings) {
		return strings == null ? null : append(strings);
	}

	private String append(final String... strings) {
		StringBuffer buffer = new StringBuffer();

		for (String str : strings) {
			buffer.append(str);
		}

		return buffer.toString();
	}
}
