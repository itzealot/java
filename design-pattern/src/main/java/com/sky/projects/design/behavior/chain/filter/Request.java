package com.sky.projects.design.behavior.chain.filter;

import java.util.Map;

/**
 * 请求对象
 * 
 * @author zealot
 */
public interface Request {

	/**
	 * 请求的Class对象
	 * 
	 * @return
	 */
	Class<?> getClassName();

	/**
	 * 获取请求的方法名称
	 * 
	 * @return
	 */
	String getMethodName();

	/**
	 * 请求参数对应的类型
	 * 
	 * @return
	 */
	Class<?>[] getParameterTypes();

	/**
	 * 请求参数
	 * 
	 * @return
	 */
	Object[] getParams();

	/**
	 * 根据 key 获取参数值
	 * 
	 * @param key
	 * @return
	 */
	String getAttribute(String key);

	/**
	 * 根据 key 获取参数值，值为 null 时返回默认值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	String getAttribute(String key, String defaultValue);

	/**
	 * 根据 key 与 value 设置请求参数的值
	 * 
	 * @param key
	 * @param value
	 */
	void setAttribute(String key, String value);

	/**
	 * 获取所有的属性及值
	 * 
	 * @return
	 */
	Map<String, String> getAttributes();

	/**
	 * 获取请求调度器
	 * 
	 * @return
	 */
	RequestDispatcher getDispatcher();
}
