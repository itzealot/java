package com.zt.design.j2ee.interceptorfilter;

/**
 * Filter
 * 
 * @author zealot
 *
 */
public interface Filter {

	/**
	 * 执行某个过滤方法
	 * 
	 * @param request
	 */
	public void execute(String request);
}
