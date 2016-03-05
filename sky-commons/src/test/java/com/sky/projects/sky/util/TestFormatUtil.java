package com.sky.projects.sky.util;

import org.junit.Test;

import com.zt.projects.sky.util.FormatPattern;
import com.zt.projects.sky.util.FormatUtil;

public class TestFormatUtil {

	@Test
	public void test() {
		System.out.println(FormatUtil.format(FormatPattern.getPattern(0, 5, true), 1000.5689));
		System.out.println(FormatUtil.format(FormatPattern.getPattern(0, 0, true), 18720077739L));
		System.out.println(FormatUtil.format("#,###.#", 18720077739L));
		System.out.println(FormatUtil.format(FormatPattern.getZeropattern(3), 56));
	}
}
