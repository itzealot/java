package com.sky.projects.design.gs;

/**
 * 请求处理接口
 * 
 * @author zealot
 */
public interface RequestHandler {

	/**
	 * 处理请求并返回结果
	 * 
	 * @param req
	 * @return
	 */
	String handle(Request req);
}
