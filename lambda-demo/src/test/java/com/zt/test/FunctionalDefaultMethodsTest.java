package com.zt.test;

import org.junit.Test;

public class FunctionalDefaultMethodsTest {

	@Test
	public void test() {
		FunctionalDefaultMethods methods = new FunctionalDefaultMethodsImpl();
		methods.method();
		methods.defaultMethod();
	}
}
