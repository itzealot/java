package com.sky.projects.ssh;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SSHsTest extends TestCase {

	public SSHsTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(SSHsTest.class);
	}

	public void testApp() {
		String host = "192.168.0.93";
		String password = "123456";
		String cmd = "ifconfig";

		System.out.println(SSHs.resultOf(host, password, cmd));
	}

	public void testApp2() {
		String host = "192.168.1.10";
		String password = "Rzx!@!*baizhao";
		String cmd = "ifconfig";

		System.out.println(SSHs.resultOf(host, password, cmd));
	}
}
