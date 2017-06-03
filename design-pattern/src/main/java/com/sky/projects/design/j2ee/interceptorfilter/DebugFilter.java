package com.sky.projects.design.j2ee.interceptorfilter;

/**
 * Debug 过滤器
 * 
 * @author zealot
 *
 */
public class DebugFilter implements Filter {

	public void execute(String request) {
		System.out.println("request log: " + request);
	}
}