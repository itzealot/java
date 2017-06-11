package com.sky.projects.jdk.lambda;

/**
 * 更好的类型推测机制
 * 
 * @author zealot
 */
public final class Value {

	public static <T> T defaultValue() {
		return null;
	}

	public static <T> T getOrDefault(T value, T defaultValue) {
		return (value != null) ? value : defaultValue;
	}

	private Value() {
	}

}