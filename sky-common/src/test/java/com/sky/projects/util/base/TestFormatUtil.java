package com.sky.projects.util.base;

import org.junit.Test;

import com.sky.projects.util.base.DecimalFormats;

public class TestFormatUtil {

	@Test
	public void test() {
		System.out.println(DecimalFormats.format(DecimalFormats.getPattern(0, 5, true), 1000.5689));
		System.out.println(DecimalFormats.format(DecimalFormats.getPattern(0, 0, true), 18720077739L));
		System.out.println(DecimalFormats.format("#,###.#", 18720077739L));
		System.out.println(DecimalFormats.format(DecimalFormats.getZeropattern(3), 56));
	}
}
