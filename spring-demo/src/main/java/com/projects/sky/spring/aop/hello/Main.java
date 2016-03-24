package com.projects.sky.spring.aop.hello;

public class Main {

	public static void main(String[] args) {
		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();

		System.out.println(arithmeticCalculator.add(2, 1));
		System.out.println(arithmeticCalculator.sub(2, 3));
		System.out.println(arithmeticCalculator.mul(2, 3));
		System.out.println(arithmeticCalculator.div(6, 3));
	}
}
