package com.sky.projects.util.common;

import org.junit.Test;

import com.sky.projects.util.SkyContext;
import com.sky.projects.util.common.SkyContextImpl;

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
