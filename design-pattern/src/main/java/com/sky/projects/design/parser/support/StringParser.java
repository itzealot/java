package com.sky.projects.design.parser.support;

import com.sky.projects.design.parser.Parser;

/**
 * String 解析器
 * 
 * @author zealot
 */
public class StringParser implements Parser<String> {

	@Override
	public String parse(String[] messages) {
		return messages == null ? null : messages[0];
	}

	@Override
	public String join(String... objs) {
		if (objs == null) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		for (String obj : objs) {
			buffer.append(obj);
		}

		return buffer.toString();
	}

	@Override
	public String[] split(String message) {
		return message == null ? null : message.split("\\|");
	}

}
