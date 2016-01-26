package com.zt.design.j2ee.interceptorfilter;

/**
 * 创建实体过滤器 AuthenticationFilter
 * 
 * @author zengtao
 *
 */
public class AuthenticationFilter implements Filter {
	/**
	 * 执行过滤方法
	 */
	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}
}
