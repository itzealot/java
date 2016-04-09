package com.sky.projects.spring.spi.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.sky.projects.spring.entity.User;
import com.sky.projects.spring.spi.UserService;

@WebService
@Service(value = "userService")
public class UserServiceImpl implements UserService {

	private Map<Long, User> users = new HashMap<Long, User>();

	{
		users.put(1L, new User(1L, "zhangsan"));
		users.put(2L, new User(2L, "lisi"));
		users.put(3L, new User(3L, "wangwu"));
		users.put(4L, new User(4L, "lili"));
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
