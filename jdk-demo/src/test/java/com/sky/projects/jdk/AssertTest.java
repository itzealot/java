package com.sky.projects.jdk;

import org.junit.Test;
import org.junit.Assert;

public class AssertTest {

	@Test
	public void assertArrayEquals() {
		Assert.assertArrayEquals("int array not equal", new int[10], new int[10]);
		Assert.assertNotEquals("int array not equal", new int[10], new int[11]);
	}

}
