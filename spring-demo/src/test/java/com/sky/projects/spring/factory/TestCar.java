package com.sky.projects.spring.factory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sky.projects.spring.entity.Car;

public class TestCar {
	private ApplicationContext applicationContext = null;

	@Test
	public void testStaticCarFactory() {
		applicationContext = new ClassPathXmlApplicationContext("beans-factory.xml");
		Car car = (Car) applicationContext.getBean("car");

		System.out.println(car);
	}

	@Test
	public void testInstaceCarFactory() {
		applicationContext = new ClassPathXmlApplicationContext("beans-factory.xml");
		Car car = (Car) applicationContext.getBean("car2");

		System.out.println(car);
	}

}
