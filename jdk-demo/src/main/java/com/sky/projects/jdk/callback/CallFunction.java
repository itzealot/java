package com.sky.projects.jdk.callback;

/**
 * 回调接口类
 * 
 * @author zealot
 *
 */
@FunctionalInterface
public interface CallFunction {

	/**
	 * 回调函数，返回状态
	 * 
	 * @param status
	 *            状态
	 */
	void call(String status);
}