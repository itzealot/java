package com.sky.projects.jdk.colection;

import java.util.Comparator;
import java.util.Map;

/**
 * 
 * @author zealot
 */
public class MapComparator<K, V extends Comparable<V>> implements Comparator<Map<K, V>> {
	private K key = null;

	// is desc(默认为降序)
	private boolean flag = true;

	public MapComparator(K key, boolean flag) {
		this.key = key;
		this.flag = flag;
	}

	public MapComparator(K key) {
		this.key = key;
	}

	@Override
	public int compare(Map<K, V> o1, Map<K, V> o2) {
		V obj1 = o1.get(key);
		V obj2 = o2.get(key);

		if (flag) {
			return obj1.compareTo(obj2);
		}

		return obj2.compareTo(obj1);
	}

}