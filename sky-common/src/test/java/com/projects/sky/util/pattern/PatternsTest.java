package com.projects.sky.util.pattern;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PatternsTest extends TestCase {
	public PatternsTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(PatternsTest.class);
	}

	public void testApp() {
		// test number
		System.out.println(Patterns.NUMBER_REGEX.matcher("12312").matches());
		System.out.println(Patterns.NUMBER_REGEX.matcher("123s12").matches());

		// test mac
		System.out.println(Patterns.MAC_REGEX.matcher("12-32-12-12-12-12").matches());
		System.out.println(Patterns.MAC_REGEX.matcher("12-3s-12-12-12-12").matches());

		// test phone
		System.out.println(Patterns.MOBILE_REGEX.matcher("8612323788").matches());
		System.out.println(Patterns.MOBILE_REGEX.matcher("862323788").matches());
		System.out.println(Patterns.MOBILE_REGEX.matcher("18720077739").matches());
		System.out.println(Patterns.MOBILE_REGEX.matcher("8618720077739").matches());
	}

}
