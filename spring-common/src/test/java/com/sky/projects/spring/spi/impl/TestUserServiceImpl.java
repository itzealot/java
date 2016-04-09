package com.sky.projects.spring.spi.impl;

import org.junit.Before;
import org.junit.Test;

import com.sky.projects.spring.spi.UserService;

public class TestUserServiceImpl {

	private UserService userService;

	@Before
	public void before() {
		userService = new UserServiceImpl();
	}

	@Test
	public void testGet() {
		System.out.println(userService.get(2L));
	}

	@Test
	public void testGetAll() {
		System.out.println(userService.getAll());
	}

}
