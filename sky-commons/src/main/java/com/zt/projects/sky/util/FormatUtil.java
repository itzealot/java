package com.zt.projects.sky.util;

import java.text.DecimalFormat;

public class FormatUtil {

	/**
	 * According to the pattern to format number and return the format String.<br />
	 * 
	 * @param pattern
	 *            the format String pattern.
	 * @param number
	 *            the number you want to format.
	 * @return
	 */
	public static <E> String format(String pattern, E number) {
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		return format.format(number);
	}
}
