package com.sky.projects.ws.demo.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import com.sky.projects.ws.demo.model.User;
import com.sky.projects.ws.demo.service.UserService;

@WebService
public class UserServiceImpl implements UserService {

	private Map<Long, User> users = new HashMap<Long, User>();

	// To init the data
	{
		users.put(1L, new User(1L, "zhangsan", "zhangsan", "I am zhangsan."));
		users.put(2L, new User(2L, "lisi", "lisi", "I am lisi"));
		users.put(3L, new User(3L, "wangwu", "wangwu", "I am wangwu"));
		users.put(4L, new User(4L, "lili", "lili", "I am lili"));
	}

	@Override
	public User get(Long id) {
		if (id == null) {
			return new User();
		}
		return users.get(id);
	}

	@Override
	public Collection<User> getAll() {
		return users.values();
	}

}