package com.zt.test.extend.subclass;

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

	/**
	 * 查询： 1.查询父类记录，只需要查询一张数据表。 2.对于子类记录，也只需要查询一张数据表。
	 * 
	 * 缺点： 1.使用了辨别者列。 2.子类独有的字段不能添加非空约束。 3.若继承层次较深，则数据表的字段也会较多。
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
	 * 插入操作： 1.对应子类对象只需要把记录插入到数据表中 2.辨别者列由Hibernate 自动维护
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
