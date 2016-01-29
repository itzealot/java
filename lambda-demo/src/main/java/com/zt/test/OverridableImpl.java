package com.zt.test;

public class OverridableImpl implements IDefaulable {
	@Override
	public String notRequired() {
		return "Overridden implementation";
	}
}