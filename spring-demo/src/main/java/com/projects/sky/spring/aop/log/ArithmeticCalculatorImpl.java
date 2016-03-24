package com.projects.sky.spring.aop.log;

import org.springframework.stereotype.Component;

import com.projects.sky.spring.aop.hello.ArithmeticCalculator;

@Component("arithmeticCalculator")
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
