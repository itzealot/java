package com.sky.projects.spring.boot;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sky.projects.spring.entity.User;

public class TestApplicationConfig {

	private AnnotationConfigApplicationContext ctx = null;

	@Before
	public void before() {
		ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@Test
	public void testGetUserBean() {
		User user = ctx.getBean(User.class);
		System.out.println(user);

		System.out.println(ctx.getBean("user"));
	}
}
