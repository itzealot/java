package com.zt.design.j2ee.interceptorfilter;

public class Client {

	// 持有 FilterManager 的实例
	FilterManager filterManager;

	public void setFilterManager(FilterManager filterManager) {
		this.filterManager = filterManager;
	}

	public void sendRequest(String request) {
		filterManager.filterRequest(request);
	}
}
