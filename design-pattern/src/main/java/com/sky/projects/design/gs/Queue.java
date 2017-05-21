package com.sky.projects.design.gs;

/**
 * 队列接口
 * 
 * @author zealot
 */
public interface Queue<T> {

	/**
	 * 添加请求
	 * 
	 * @param obj
	 */
	void addRequest(T obj);

	/**
	 * 获取请求
	 * 
	 * @return
	 */
	T getRequest();
}
