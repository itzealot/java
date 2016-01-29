package com.zt.design.j2ee.interceptorfilter;

public class Target {
	public void execute(String request) {
		System.out.println("Executing request: " + request);
	}
}
