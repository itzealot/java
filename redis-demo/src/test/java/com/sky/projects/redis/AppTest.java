package com.sky.projects.redis;

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
		String str = "17|21312|asjdasd|213123";
		String[] arrays = str.split("\\|");
		for (String s : arrays) {
			System.out.println(s);
		}
	}

	public void testApp2() {
		String str = "17|21312|asjdasd|213123";
		String[] arrays = str.split("|");
		for (String s : arrays) {
			System.out.println(s);
		}
	}

	public void testApp3() {
		String str = "17,21312,asjdasd,213123";
		String[] arrays = str.split(",");
		for (String s : arrays) {
			System.out.println(s);
		}
	}
}
