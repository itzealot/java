package com.projects.sky.util.common;

import java.util.HashMap;
import java.util.Map;

public class SkyContext implements Context {

	Map<String, Object> values = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) values.get(key);
	}

	public Map<String, Object> put(String key, Object value) {
		values.put(key, value);

		return values;
	}

	public String toString() {
		return values.toString();
	}

}