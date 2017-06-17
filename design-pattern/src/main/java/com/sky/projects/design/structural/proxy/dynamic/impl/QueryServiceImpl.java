package com.sky.projects.design.structural.proxy.dynamic.impl;

import com.sky.projects.design.structural.proxy.dynamic.QueryService;

public class QueryServiceImpl implements QueryService {

	@Override
	public String service(String msg) {
		return "service:" + msg;
	}

}
