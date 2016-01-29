package com.zt.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apusic.projects.gateway.ApplicationConfig;
import com.apusic.projects.gateway.entity.User;

public class TestApplicationConfig {

	// AnnotationConfigApplicationContext ctx object
	private AnnotationConfigApplicationContext ctx = null;

	@Before
	public void before() {
		// To init the ctx object by Class<T>
		ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@Test
	public void testGetUserBean() {
		// To get Bean by Class<T> object
		User user = ctx.getBean(User.class);
		System.out.println(user);
		
		// To get Bean by name, is a singleton
		System.out.println(ctx.getBean("user"));
	}
}
