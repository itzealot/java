package com.sky.projects.design.structural.proxy.dynamic.impl;

import com.sky.projects.design.structural.proxy.dynamic.QueryDao;

/**
 * 被代理类(构建时间较长)
 * 
 * @author zealot
 */
public class QueryDaoImpl implements QueryDao {

	public QueryDaoImpl() {
		try {
			// 重操作，可能包含数据库连接等耗时操作
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public String request() {
		return this.toString() + " Request String";
	}

}
