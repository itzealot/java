package com.projects.sky.spring.aop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.projects.sky.spring.aop.hello.ArithmeticCalculator;

public class Main {

	private static ApplicationContext applicationContext = null;

	public static void main(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		// 2.从IOC 容器中获取bean的实例
		ArithmeticCalculator arithmeticCalculator = applicationContext.getBean(ArithmeticCalculator.class);

		// 3.使用bean
		int result = arithmeticCalculator.add(2, 3);
		System.out.println("result = " + result);

		// 抛出异常
		// result = arithmeticCalculator.div(6, 0);
		// System.out.println("result = " + result);
	}
}
