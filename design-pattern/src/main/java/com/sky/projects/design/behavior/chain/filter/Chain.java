package com.sky.projects.design.behavior.chain.filter;

/**
 * 请求链
 * 
 * @author zealot
 *
 */
public interface Chain<T> {

	void setRequest(Request request);

	Request getRequest();

	Chain<T> getChain();

	void setChain(Chain<T> chain);

	/**
	 * 请求放行
	 * 
	 * @param request
	 */
	void doFilter(Filter<T> filter, T request);

	Filter<T> getFilter();

	void getFilter(Filter<T> filter);
}
