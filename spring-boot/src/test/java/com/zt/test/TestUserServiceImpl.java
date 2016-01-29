package com.zt.test;

import org.junit.Before;
import org.junit.Test;

import com.apusic.projects.gateway.ws.service.IUserService;
import com.apusic.projects.gateway.ws.service.impl.UserServiceImpl;

public class TestUserServiceImpl {

	private IUserService userService;

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
