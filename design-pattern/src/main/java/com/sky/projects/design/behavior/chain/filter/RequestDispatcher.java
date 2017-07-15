package com.sky.projects.design.behavior.chain.filter;

/**
 * 请求调度器
 * 
 * @author zealot
 */
public interface RequestDispatcher {

	/**
	 * 转发请求
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	void dispatch(Request req, Response res) throws Exception;

}
