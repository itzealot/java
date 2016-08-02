package com.sky.projects.util.common;

/**
 * Pair
 * 
 * @author zt
 */
public class Pair<K, V> {
	private K key;
	private V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K key() {
		return key;
	}

	public K first() {
		return key;
	}

	public V second() {
		return value;
	}

	public V value() {
		return value;
	}

	@Override
	public String toString() {
		return "{key=" + key + ", value=" + value + "}";
	}
}
