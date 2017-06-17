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
			System.out.println("impl.......");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public String request() {
		return this.toString() + " Request String";
	}

	public void deal() {
		System.out.println("deal real message.......");
	}

	@Override
	public String deal(String msg) {
		return "deal:" + msg;
	}

}
