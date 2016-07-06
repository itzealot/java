package com.projects.sky.util.common;

public class Holder<T> {
	private volatile T value;

	public T get() {
		return value;
	}

	public void set(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{value=" + value + "}";
	}
}
