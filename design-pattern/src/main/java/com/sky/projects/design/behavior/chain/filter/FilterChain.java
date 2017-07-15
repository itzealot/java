package com.sky.projects.design.behavior.chain.filter;

/**
 * 请求链
 * 
 * @author zealot
 */
public interface FilterChain {

	/**
	 * 执行请求
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	void doFilter(Request req, Response res) throws Exception;
}
