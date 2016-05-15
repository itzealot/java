package com.sky.projects.mock.dao;

import com.sky.projects.mock.entity.User;

public class UserDao {

	public User getById(Long id) {
		return new User(id, "id=" + id, "My id is " + id);
	}
}