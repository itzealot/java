package com.sky.projects.jdk.lambda;

/**
 * 更好的类型推测机制
 * 
 * @author zt
 *
 * @param <T>
 */
public class Value<T> {

	public static <T> T defaultValue() {
		return null;
	}

	public T getOrDefault(T value, T defaultValue) {
		return (value != null) ? value : defaultValue;
	}

}