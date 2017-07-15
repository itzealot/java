package com.sky.projects.design.behavior.chain.filter;

public interface Filter {

	/**
	 * 根据 FilterConfig 执行有必要的初始化
	 * 
	 * @param config
	 * @throws Exception
	 */
	void init(FilterConfig config) throws Exception;

	/**
	 * 请求放行
	 * 
	 * <code>
	 * 	请求放行，执行 FilterChain 的后续 Filter
	 * 	chain.doFilter(req, res);
	 * 
	 * 请求结束，则直接利用 Response 返回对应的结果
	 * </code>
	 * 
	 * @param req
	 * @param res
	 * @param chain
	 * @throws Exception
	 */
	void doFilter(Request req, Response res, FilterChain chain) throws Exception;

	/**
	 * destroy
	 */
	void destroy();

}
