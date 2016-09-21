package com.sky.projects.design.gs;

/**
 * 请求对象
 * 
 * @author zealot
 */
public class Request {

	private String name;

	public Request(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Request [name=" + name + "]";
	}

}
