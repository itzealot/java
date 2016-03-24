package com.zt.test.n2n.multiple;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestMany2ManyMultiple {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

	}

	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void testSave() {
		Category category1 = new Category();
		category1.setName("C-AA");
		Category category2 = new Category();
		category2.setName("C-BB");

		Item item1 = new Item();
		item1.setName("I-AA");

		Item item2 = new Item();
		item2.setName("I-BB");

		// ָ��������ϵ
		category1.getItems().add(item1);
		category1.getItems().add(item2);

		category2.getItems().add(item1);
		category2.getItems().add(item2);

		// ִ�б������
		session.save(category1);
		session.save(category2);
		session.save(item1);
		session.save(item2);
	}

	@Test
	public void testGet() {
		Category category = (Category) session.get(Category.class, 1);
		System.out.println(category.getName());
		// ��Ҫ�����м��
		Set<Item> items = category.getItems();
		System.out.println(items.size());
	}
}
