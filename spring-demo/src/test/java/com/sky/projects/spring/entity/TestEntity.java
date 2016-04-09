package com.sky.projects.spring.entity;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEntity {
	private ApplicationContext applicationContext = null;

	@Test
	public void testPerson() {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		Person person = (Person) applicationContext.getBean("person");

		System.out.println(person.toString());
	}
}
