package com.zt.test.one21.foreign;

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
public class Test121 {
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
		Department department = new Department();
		department.setDepartName("DEPT-AA");

		Manager manager = new Manager();
		manager.setManagerName("MGR-AA");

		// 设定关联关系
		department.setManager(manager);

		manager.setDepartment(department);

		// 保存操作，2条INSERT语句
		// 建议先保存没有外键列的那个对象，这样会减少UPDATE语句。
		session.save(manager);
		session.save(department);

		// 保存操作，会多出更新语句
		// session.save(department);
		// session.save(manager);
	}

	@Test
	public void testDelete() {

	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testGet2() {
		// 在查询没有外键的实体对象时，使用的左外连接查询，一并查询出其关联的对象
		// 并已经进行初始化。
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getManagerName());
		System.out.println(manager.getDepartment().getDepartName());
	}

	@Test
	public void testGet() {
		// 1). 默认情况下对关联属性使用懒加载。
		// 2). 所以可能会出现懒加载异常，当session关闭时，而又使用相应的属性（成员属性）。
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDepartName());

		// 3). 查询Manger 对象的连接条件应该是department.manager_id = manager_id，
		// 而不应该是dept_depart_id = manager.manager_id
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getManagerName());
	}
}
