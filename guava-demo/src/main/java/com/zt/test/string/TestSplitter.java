package com.zt.test.string;

import org.junit.Test;

import com.google.common.base.Splitter;

public class TestSplitter {

	@Test
	public void testSplit() {
		Iterable<String> iterator = Splitter.on(',').trimResults()
				.omitEmptyStrings().split("foo,bar,,   qux");
		System.out.println(iterator.toString());
	}
}
