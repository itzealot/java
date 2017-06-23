package com.sky.projects.design.structural.proxy.dynamic;

public interface QueryDao {

	/**
	 * 获取请求
	 * 
	 * @return
	 */
	String request();

	/**
	 * deal message
	 * 
	 * @param msg
	 */
	String deal(String msg);
}
