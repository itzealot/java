package com.zt.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.entity.User;
import com.zt.repository.UserRepository;

public class TestUserRepository {
	private UserRepository repository;
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Before
	public void before() {
		repository = applicationContext.getBean(UserRepository.class);
	}

	@Test
	public void save() {
		repository.save(new User(null, "lisiss", "lisiss@qq.com", 12));
	}

	@Test
	public void test() {
		repository.test();
	}

}
