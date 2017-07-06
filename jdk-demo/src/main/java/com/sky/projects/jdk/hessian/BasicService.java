package com.sky.projects.jdk.hessian;

import com.caucho.hessian.server.HessianServlet;

public class BasicService extends HessianServlet implements BasicAPI {
	private static final long serialVersionUID = -3403161810770369490L;

	private String greeting = "Hello, world";

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	@Override
	public String hello() {
		return greeting;
	}
}