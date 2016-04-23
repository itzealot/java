package com.projects.sky.util.common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapHolder {
	private static Map<String, Object> holder = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		return (T) holder.get(key);
	}

	public static <T> Map<String, Object> put(String key, T value) {
		holder.put(key, value);

		return holder;
	}

	public static Collection<Object> values() {
		return holder.values();
	}

	public static Set<String> keys() {
		return holder.keySet();
	}

	public static boolean containsKey(String key) {
		return holder.containsKey(key);
	}

	public static boolean containsValue(Object value) {
		return holder.containsValue(value);
	}

	public static boolean isEmpty() {
		return holder.isEmpty();
	}

	public static int size() {
		return holder.size();
	}

	public static String mapString() {
		return holder.toString();
	}
}
