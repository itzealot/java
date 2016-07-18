package com.sky.projects.design.structural.proxy.dynamic.supprot;

import com.sky.projects.design.structural.proxy.dynamic.QueryDao;

public class QueryDaoImpl implements QueryDao {

	public QueryDaoImpl() {
		// 重操作，可能包含数据库连接等耗时操作
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public String request() {
		return "Request String";
	}

}
