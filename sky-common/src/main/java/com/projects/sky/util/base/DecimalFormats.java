package com.projects.sky.util.base;

import java.text.DecimalFormat;

public class DecimalFormats {

	public static <E> String format(String pattern, E number) {
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		return format.format(number);
	}
}
