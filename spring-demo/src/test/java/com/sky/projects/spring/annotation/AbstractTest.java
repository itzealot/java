package com.sky.projects.spring.annotation;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractTest extends TestCase {
	protected ApplicationContext context = null;

	{
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-annotation.xml");
	}

}