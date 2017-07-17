package com.sky.projects.jdk.loadbalance;

public interface InvokeContext {

	String get(String key);

	String getValue(String key, String defaultValue);

	int getValue(String key, int defaultValue);

	long getValue(String key, long defaultValue);

	int getMethodParameter(String key, String hashKey, int defaultValue);

	String getMethodParameter(String key, String hashKey, String defaultValue);

}
