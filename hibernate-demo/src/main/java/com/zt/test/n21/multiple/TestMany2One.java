package com.zt.test.n21.multiple;

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
	public void testCascade() {
		/**
		 * 测试cascade属性中的delete-orphan值。
		 */
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrder().clear();
	}

	@Test
	public void testMany2OneDelete() {

		// cascade属性中的delete值。get(Class.class, OID);
		Customer customer = (Customer) session.get(Customer.class, 1);

		session.delete(customer);
	}

	@Test
	public void testMany2OneUpdate() {
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrder().iterator().next().setOrderName("GGG");
	}

	@Test
	public void testOne2ManyGet() {
		// 1).对n的一端的集合使用延迟加载
		Customer customer = (Customer) session.get(Customer.class, 1);

		// 2).返回多的一端的集合是Hibernate内置的集合类型。
		// 该类型具有延迟加载和存放代理对象的功能。
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getOrder().getClass());

		// 3).可能会抛出LazyInitializationException异常，当session.close()时，又使用代理的对象。

		// 4).在需要使用集合中元素的时候进行初始化。
	}

	@Test
	public void testMany2OneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("CC");

		Order order1 = new Order();
		order1.setOrderName("order-5");

		Order order2 = new Order();
		order2.setOrderName("order-6");

		// 设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		// add into set
		customer.getOrder().add(order1);
		customer.getOrder().add(order2);
		// 可以在1的一端的set节点指定inverse="true"， 来使1的一端放弃维护关联关系！
		// 建议设定set的inverse="true"，建议先插入1的一端，后插入多的一端，好出是
		// 不会多出Update语句
		session.save(customer);

		session.save(order1);
		session.save(order2);
		// 执行save操作:先插入Customer，再插入Order，3条INSERT,2条Update。
		// 先插入1的一端，在插入n的一端，只有INSERT语句。
		// 因为1的一端和n的一端都维护关联关系，所以会多出UPDATE
		// session.save(customer);
		//
		// session.save(order1);
		// session.save(order2);

		// 先插入Order，再插入Customer，3条INSERT，4条UPDATE语句
		// 先插入n的一边，再插入一的一边，会多出UPDATE语句。
		// 因为在插入多的一端时，无法确定1的一端的外键值，所有只能等一的一端插入后，再额外发送UPDATE语句。
		// 推荐先插入1的一边，再插入多的一边。
		// session.save(order1);
		// session.save(order2);
		//
		// session.save(customer);
	}

}
