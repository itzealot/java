package com.zt.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.entity.Person;

public class TestPerson {
	private ApplicationContext applicationContext = null;
	// EntityManagerFactory 对象
	private EntityManagerFactory entityManagerFactory = null;

	// EntityManager 对象
	private EntityManager entityManager = null;

	// 用于事务的EntityTransaction 对象
	private EntityTransaction transaction = null;

	@Before
	public void before() {

		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		entityManagerFactory = applicationContext
				.getBean(EntityManagerFactory.class);

		// 2. 根据entityManagerFactory 创建EntityManager 对象
		entityManager = entityManagerFactory.createEntityManager();

	}

	@Test
	public void testPersist() {

		// 3. 通过EntityManager 对象开启事务
		transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 通过EntityManager 对象进行持久化操作
		Person person = new Person(null, "zhangsan", "zhangsan@qq.com", 18);
		entityManager.persist(person);

		// 5. 提交事务
		transaction.commit();
	}

	@After
	public void after() {
		// 6. 关闭EntityManager
		entityManager.clear();

		// 7. 关闭EntityManagerFactory
		entityManagerFactory.close();
	}
}
