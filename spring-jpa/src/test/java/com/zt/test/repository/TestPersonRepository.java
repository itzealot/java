package com.zt.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.entity.Person;
import com.zt.repository.PersonRepository;

public class TestPersonRepository {
	private PersonRepository repository;
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Before
	public void before() {
		repository = applicationContext.getBean(PersonRepository.class);
	}

	@Test
	public void testSave() {
		repository.save(new Person(null, "lisi", "lisi@qq.com", 12));
	}

	@Test
	public void testFindByEmail() {
		System.out.println(repository.findByEmail("lisi@qq.com"));
	}

	@Test
	public void testFindByLastName() {
		System.out.println(repository.findByLastName("zhangsan"));
	}
}
