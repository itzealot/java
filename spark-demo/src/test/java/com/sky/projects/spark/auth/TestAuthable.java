package com.sky.projects.spark.auth;

import org.apache.spark.streaming.api.java.JavaDStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@SuppressWarnings("serial")
public class TestAuthable extends TestCase {
	public TestAuthable(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TestAuthable.class);
	}

	public void testApp() {
		Authable authable = new Authable() {
			@Override
			public JavaDStream<String[]> extractAuthData(JavaDStream<String[]> source) {
				System.out.println("extractAuthData..............");
				return null;
			}
		};

		JavaDStream<String[]> stream = authable.extractAuthData(null);
		System.out.println(stream);
	}
}
