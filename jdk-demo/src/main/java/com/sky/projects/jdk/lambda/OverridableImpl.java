package com.sky.projects.jdk.lambda;

public class OverridableImpl implements IDefaulable {
	@Override
	public String notRequired() {
		return "Overridden implementation";
	}
}