package com.projects.sky.util.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Maps {

	public static <K, V> Collection<V> values(Map<K, V> map) {
		return map == null ? null : map.values();
	}

	public static <K, V> V get(Map<K, V> map, K key) {
		if (Validates.validate(map)) {
			return map.get(key);
		}

		return null;
	}

	public static <K, V> String iteratorMap(Map<K, V> map) {
		if (!Validates.validate(map)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<K, V> entry = it.next();

			K key = entry.getKey();
			V value = entry.getValue();

			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			if (it.hasNext()) {
				buffer.append(", ");
			}
		}

		buffer.append("}");

		return buffer.toString();
	}

	public static <K, V> String entryFEachMap(Map<K, V> map) {
		if (!Validates.validate(map)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		for (Map.Entry<K, V> entry : map.entrySet()) {

			K key = entry.getKey();
			V value = entry.getValue();

			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			buffer.append(", ");
		}
		return buffer.substring(0, buffer.length() - 2) + "}";
	}

	public static <K, V> String keySetIteratorMap(Map<K, V> map) {
		if (!Validates.validate(map)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		for (Iterator<K> it = map.keySet().iterator(); it.hasNext();) {
			K key = it.next();
			V value = map.get(key);

			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			buffer.append(", ");
		}

		return buffer.substring(0, buffer.length() - 2) + "}";
	}

	public static <K, V> String keySetFEachMap(Map<K, V> map) {
		if (!Validates.validate(map)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		for (K key : map.keySet()) {
			V value = map.get(key);
			buffer.append("{");
			buffer.append(key.toString());

			buffer.append(" : ");
			buffer.append(value.toString());

			buffer.append("}");

			buffer.append(", ");
		}

		return buffer.substring(0, buffer.length() - 2) + "}";
	}
}
