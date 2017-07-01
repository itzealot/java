package com.sky.projects.design.behavior.chain.filter;

public interface Filter<T> {

	/**
	 * 请求是否放行
	 * 
	 * @param param
	 */
	boolean filter(Request request, T param);

}
