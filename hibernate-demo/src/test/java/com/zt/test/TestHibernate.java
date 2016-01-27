package com.zt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zt.test.model.User;

@SuppressWarnings("deprecation")
public class TestHibernate {
	private SessionFactory sessionFactory = null;
	private Session session = null;

	@Before
	public void before() {

		// 创建 Configuration 对象: 对应 hibernate 的基本配置信息和 对象关系映射信息
		Configuration configuration = new Configuration().configure();

		// 创建一个 ServiceRegistry 对象: hibernate 4.x 新添加的对象
		// hibernate 的任何配置和服务都需要在该对象中注册后才能有效.
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// 创建Session 对象
		session = sessionFactory.openSession();
	}

	@Test
	public void test() {

	}

	@After
	public void after() {
		if (session != null) {
			session.close();
		}
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	@Test
	public void save() {
		// 3. 开启事务
		Transaction transaction = session.beginTransaction();
		// 4. 执行保存操作
		User news = new User(null, "MISS1", "MISS1", new java.util.Date(), "MISS1");
		session.save(news);
		System.out.println(news.getId());
		// 5. 提交事务
		transaction.commit();
	}

	@Test
	public void get() {
		User news = (User) session.get(User.class, 13);
		System.out.println(news);
	}

}
