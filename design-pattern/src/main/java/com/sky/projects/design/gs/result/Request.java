package com.sky.projects.design.gs.result;

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

	// 参考 future 实现，请求的返回值
	private Data response;

	public synchronized void setResponse(Data response) {
		this.response = response;
	}

	public synchronized Data getResponse() {
		return response;
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
