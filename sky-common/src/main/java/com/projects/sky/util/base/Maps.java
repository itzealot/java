package com.projects.sky.util.base;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public final class Maps {

	public static <K, V> V get(Map<K, V> map, K key) {
		return map == null ? null : map.get(key);
	}

	public static <K, V> Set<K> keys(Map<K, V> map) {
		return map == null ? Sets.newHashSet() : map.keySet();
	}

	public static <K, V> Collection<V> values(Map<K, V> map) {
		return map == null ? Lists.newArrayList() : map.values();
	}

	private Maps() {
	}

}
