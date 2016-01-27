package com.zt.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.zt.dao.UserDao;
import com.zt.entity.User;

/**
 * 自定义接口的实现类
 * 
 * @author zt
 *
 */
public class UserRepositoryImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void test() {
		System.out.println("-->" + entityManager.find(User.class, 1L));
	}

}
