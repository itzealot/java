package com.projects.sky.util.base;

import org.junit.Assert;
import org.junit.Test;

public class TestResourceBundles {
	private String baseName = "com.zt.projects.sky.util.test";
	private String key = "myKey";

	@Test
	public void getString() {
		Assert.assertEquals("The value is not equal", "myValue", ResourceBundles.getString(key, baseName));
	}
}
