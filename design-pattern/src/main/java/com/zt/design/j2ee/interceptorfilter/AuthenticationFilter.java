package com.zt.design.j2ee.interceptorfilter;

/**
 * Authentication 过滤器
 * 
 * @author zengtao
 *
 */
public class AuthenticationFilter implements Filter {

	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}
}
