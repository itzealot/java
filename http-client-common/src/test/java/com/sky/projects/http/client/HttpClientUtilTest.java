package com.sky.projects.http.client;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HttpClientUtilTest extends TestCase {

	public HttpClientUtilTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(HttpClientUtilTest.class);
	}

	public void testHttpGet() {
		String url = "http://www.runoob.com/java/java-regular-expressions.html";
		System.out.println(HttpClientUtil.httpGetRequest(url));
	}
}
