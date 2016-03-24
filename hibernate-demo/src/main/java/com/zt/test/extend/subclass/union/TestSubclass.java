package com.zt.test.extend.subclass.union;

import java.util.List;

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
public class TestSubclass {

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
	public void testUpdateParent() {
		String hql = "UPDATE Person p SET p.age = 20";
		session.createQuery(hql).executeUpdate();
	}

	@Test
	public void testUpdate() {
		String hql = "UPDATE Student SET age = 20";
		session.createQuery(hql).executeUpdate();
	}

	/**
	 * 查询： 缺点： 1.存在冗余的字段 2.若更新父表的字段，则更新的效率较低。
	 * 
	 * 优点： 1.不需要使用辨别者列。 2.子类独有的字段能添加非空约束。
	 */
	@Test
	public void testQuery() {
		@SuppressWarnings("unchecked")
		List<Person> persons = session.createQuery("FROM Person").list();

		System.out.println(persons.size());

		@SuppressWarnings("unchecked")
		List<Student> students = session.createQuery("FROM Student").list();

		System.out.println(students.size());
	}

	/**
	 * 插入操作： 1.对应子类对象只需要把记录插入到一张数据表中
	 */
	@Test
	public void testSave() {
		Person person = new Person();
		person.setName("AA");
		person.setAge(23);

		session.save(person);

		Student student = new Student();
		student.setName("BB");
		student.setAge(22);
		student.setSchool("ECJTU");
		session.save(student);
	}
}
