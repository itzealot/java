package com.projects.sky.util.common;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JoinsTest extends TestCase {
	public JoinsTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(JoinsTest.class);
	}

	public void testApp() {
		System.out.println(Appender.append("a", "B"));
		System.out.println(Appender.appends('a', 'B'));
		System.out.println(Appender.appends('1', '2'));
		System.out.println(Appender.appends(true, false));
	}

}
