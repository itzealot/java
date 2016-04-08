package com.sky.projects.jdk.lambda;

/**
 * 更好的类型推测机制
 * 
 * @author zt
 *
 * @param <T>
 */
public class Value<T> {
	/**
	 * default value is null
	 * 
	 * @return
	 */
	public static <T> T defaultValue() {
		return null;
	}

	/**
	 * get value or default value, if value is not null, then return value; else
	 * return defaultValue
	 * 
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public T getOrDefault(T value, T defaultValue) {
		return (value != null) ? value : defaultValue;
	}

}