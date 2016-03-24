package com.zt.test.n21;

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
public class TestMany2One {

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
	public void testMany2OneDelete() {
		// 在不设定级联关系情况下，且1这一端的对象有n的对象在引用，则不能直接删除1这一端的对象。
		Customer customer = (Customer) session.get(Customer.class, 1);

		session.delete(customer);
	}

	@Test
	public void testMany2OneUpdate() {
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("AAA");
	}

	@Test
	public void testMany2OneGet() {

		// 1).若查询多的一端的一个对象，则默认情况下，只查询了多的一端的对象，而没有查询关联的1那一端的对象！。
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderName());
		System.out.println(order.getCustomer().getClass().getName());

		// 2).在需要使用到关联的对象时，才发送对应的sql语句。

		Customer customer = order.getCustomer();
		System.out.println(customer.getCustomerName());

		// 3).在查询Customer对象时，由多的一端导航1的一端时，
		// 若此时session关闭，则默认情况下会发生LazyIntializationException（懒加载异常）

		// 4).获取Order 对象时，默认情况下，其关联的Customer对象是一个代理对象！
	}

	@Test
	public void testMany2OneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("BB");

		Order order1 = new Order();
		order1.setOrderName("order-3");

		Order order2 = new Order();
		order2.setOrderName("order-4");

		// 设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		// 执行save操作:先插入Customer，再插入Order，3条INSERT。
		// 先插入1的一端，在插入n的一端，只有INSERT语句。
		session.save(customer);

		session.save(order1);
		session.save(order2);

		// 先插入Order，再插入Customer，3条INSERT，2条UPDATE语句

		// 先插入n的一边，再插入一的一边，会多出UPDATE语句。
		// 因为在插入多的一端时，无法确定1的一端的外键值，所有只能等一的一端插入后，再额外发送UPDATE语句。

		// 推荐先插入1的一边，再插入多的一边。
		// session.save(order1);
		// session.save(order2);
		//
		// session.save(customer);
	}

}
