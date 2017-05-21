package com.zt.design.j2ee.interceptorfilter;

/**
 * Authentication 过滤器
 * 
 * @author zealot
 *
 */
public class AuthenticationFilter implements Filter {

	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}
}
