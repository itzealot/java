package com.sky.projects.design.structural.proxy.dynamic.javassist;

public class CounterImpl implements Counter {
	private int count = 0;

	@Override
	public int count() {
		return count++;
	}

}
