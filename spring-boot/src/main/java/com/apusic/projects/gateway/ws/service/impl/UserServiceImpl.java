package com.apusic.projects.gateway.ws.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.apusic.projects.gateway.entity.User;
import com.apusic.projects.gateway.ws.service.IUserService;

@WebService
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	private Map<Long, User> users = new HashMap<Long, User>();

	// To init the data
	{
		users.put(1L, new User(1L, "zhangsan"));
		users.put(2L, new User(2L, "lisi"));
		users.put(3L, new User(3L, "wangwu"));
		users.put(4L, new User(4L, "lili"));
	}

	@Override
	public User get(Long id) {
		// TODO Auto-generated method stub
		if (id == null) {
			return new User();
		}
		return users.get(id);
	}

	@Override
	public Collection<User> getAll() {
		// TODO Auto-generated method stub
		return users.values();
	}

}
