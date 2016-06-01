package com.projects.sky.util.algorithm;

import java.util.Iterator;

import com.projects.sky.util.algorithm.AhoCorasick;
import com.projects.sky.util.algorithm.SearchResult;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AhoCorasickTest extends TestCase {
	public AhoCorasickTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AhoCorasickTest.class);
	}

	public void testApp() {
		AhoCorasick<String> acinfo = new AhoCorasick<String>();
		acinfo.add("hello".getBytes(), "has match");
		acinfo.prepare();

		Iterator<SearchResult<String>> scResults = acinfo.search("hello, I love u.".getBytes());

		if (scResults.hasNext()) {
			System.out.println(scResults.next().getOutputs());
		} else {
			System.out.println(scResults.hasNext());
		}
	}
}
