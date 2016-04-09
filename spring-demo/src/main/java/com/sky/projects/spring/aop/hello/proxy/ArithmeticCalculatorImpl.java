package com.sky.projects.spring.aop.hello.proxy;

import com.sky.projects.spring.aop.hello.ArithmeticCalculator;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	public int add(int i, int j) {
		return i + j;
	}

	public int sub(int i, int j) {
		return i - j;
	}

	public int mul(int i, int j) {
		return i * j;
	}

	public int div(int i, int j) {
		return i / j;
	}

}
