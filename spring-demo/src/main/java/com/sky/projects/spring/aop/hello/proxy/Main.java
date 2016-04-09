package com.sky.projects.spring.aop.hello.proxy;

import com.sky.projects.spring.aop.hello.ArithmeticCalculator;

public class Main {

	public static void main(String[] args) {
		ArithmeticCalculator target = new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = new ArithmeticCalculatorProxy(target).getProxy();

		System.out.println(proxy.add(1, 2));
	}
}
