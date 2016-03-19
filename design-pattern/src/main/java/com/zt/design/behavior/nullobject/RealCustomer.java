package com.zt.design.behavior.nullobject;

/**
 * 真实的客户，客户有自定义的名称
 * 
 * @author zt
 *
 */
public class RealCustomer extends AbstractCustomer {

	public RealCustomer(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isNil() {
		return false;
	}
}