package com.sky.projects.spring.configuration;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sky.projects.spring.configuration.service.OrderService;

public class TestApplicationConfig {
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

	@Test
	public void test() {
		OrderService orderService = ctx.getBean(OrderService.class);
		System.out.println(orderService);
	}
}
