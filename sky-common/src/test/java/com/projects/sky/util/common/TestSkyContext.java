package com.projects.sky.util.common;

import org.junit.Test;

import com.projects.sky.util.SkyContext;

public class TestSkyContext {
	SkyContext context = new SkyContextImpl();

	{
		context.put("int", 1);
		context.put("double", 1.0);
		context.put("string", "string");
	}

	@Test
	public void test() {
		int value = context.get("int");

		System.out.println(value);
	}
}
