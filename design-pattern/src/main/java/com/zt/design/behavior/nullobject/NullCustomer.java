package com.zt.design.behavior.nullobject;

/**
 * 没有名称的客户对象，则名称为特定值
 * 
 * @author zt
 */
public class NullCustomer extends AbstractCustomer {

	private final String DEFAULT_NAME = "Not Available in Customer Database";

	@Override
	public String getName() {
		return DEFAULT_NAME;
	}

	@Override
	public boolean isNil() {
		return true;
	}
}