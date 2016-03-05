package com.zt.design.behavior.nullobject;

public abstract class AbstractCustomer {
	// 名称
	protected String name;

	/**
	 * 名称是否为 null
	 * 
	 * @return 是 null，返回 true;否则返回 false;
	 */
	public abstract boolean isNil();

	/**
	 * 获取名称，若名称为null，则返回默认字符串
	 * 
	 * @return
	 */
	public abstract String getName();
}