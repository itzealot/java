package com.apusic.sicp;

import java.sql.Date;

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

		System.out.println("我爱你".length());
		System.out.println("daasd".length());

		long time = 1439306885l * 1000;
		System.out.println(new Date(time));
	}

	public void testBytes() {
		System.out.println("1439306885".getBytes().length);
		System.out.println("1439306885".getBytes()[0]);
		System.out.println(toBytes(Long.valueOf(1439306885l)).length);
		System.out.println(Integer.valueOf(1439306885));

		System.out.println(new java.util.Date().getTime() / 1000);
	}

	public byte[] toBytes(long value) {
		return new byte[8];
	}

	public byte[] toBytes(int value) {
		return new byte[4];
	}
}
