package com.sky.projects.mock.service.impl;

import com.sky.projects.mock.dao.UserDao;
import com.sky.projects.mock.entity.User;
import com.sky.projects.mock.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public User query(Long id) {
		return userDao.getById(id);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
