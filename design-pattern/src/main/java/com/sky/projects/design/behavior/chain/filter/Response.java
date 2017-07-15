package com.sky.projects.design.behavior.chain.filter;

/**
 * 请求响应
 * 
 * @author zealot
 */
public interface Response {

	/**
	 * 获取请求响应后的结果
	 * 
	 * @return
	 */
	Result getResult();

	boolean hasException();

	Throwable getException();

	/**
	 * 响应是否超时
	 * 
	 * @return
	 */
	boolean isTimeout();

}
