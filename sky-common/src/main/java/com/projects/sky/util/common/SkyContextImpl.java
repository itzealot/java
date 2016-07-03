package com.projects.sky.util.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;
import com.projects.sky.util.SkyContext;

public class SkyContextImpl implements SkyContext {
	private Map<String, Object> map;

	public SkyContextImpl() {
		this.map = new HashMap<>();
	}

	public SkyContextImpl(Map<String, Object> map) {
		this.map = map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		return (T) map.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Set<String> keys() {
		return map.keySet();
	}

	@Override
	public Set<String> keys(KeyFilter filter) {
		Set<String> keys = keys();
		Set<String> results = Sets.newHashSet();

		for (String key : keys) {
			if (filter.accept(key)) {
				results.add(key);
			}
		}

		return results;
	}

	@Override
	public Collection<Object> values() {
		return map.values();
	}

	@Override
	public String toString() {
		return map.toString();
	}

}