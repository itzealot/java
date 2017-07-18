package com.sky.projects.design.behavior.chain.filter;

/**
 * 请求响应结果
 * 
 * @author zealot
 */
public interface Result {

	/**
	 * 获取结果的值
	 * 
	 * @return
	 */
	Object getValue();

	/**
	 * 获取结果对应的内容类型
	 * 
	 * @return
	 */
	String getContentType();

}
