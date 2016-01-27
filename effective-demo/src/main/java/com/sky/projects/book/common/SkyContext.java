package com.sky.projects.book.common;

import java.util.HashMap;
import java.util.Map;

public class SkyContext implements Context {

	Map<String, Object> values = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		return (T) values.get(key);
	}

	@Override
	public <T> Map<String, Object> put(String key, T value) {
		values.put(key, value);
		return values;
	}

	@Override
	public String toString() {
		return values.toString();
	}

}