package com.sky.projects.spring.aop.hello;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	public int add(int i, int j) {
		System.out.println("before add...");

		int result = i + j;

		System.out.println("after add...");

		return result;
	}

	public int sub(int i, int j) {
		System.out.println("before sub...");

		int result = i - j;

		System.out.println("after sub...");

		return result;
	}

	public int mul(int i, int j) {
		System.out.println("before mul...");

		int result = i * j;

		System.out.println("after mul...");

		return result;
	}

	public int div(int i, int j) {

		System.out.println("before div...");
		int result = i / j;
		System.out.println("after div...");

		return result;
	}

}
