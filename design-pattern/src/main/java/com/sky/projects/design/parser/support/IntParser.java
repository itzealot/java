package com.sky.projects.design.parser.support;

import com.sky.projects.design.parser.Parser;

public class IntParser implements Parser<Integer> {

	@Override
	public Integer parse(Integer[] messages) {
		return messages == null ? null : messages[0];
	}

	@Override
	public Integer join(Integer... objs) {
		if (objs == null) {
			return null;
		}

		int sum = 0;
		for (Integer obj : objs) {
			if (obj != null)
				sum += obj;
		}

		return sum;
	}

	@Override
	public Integer[] split(Integer message) {
		return new Integer[] { message };
	}

}
