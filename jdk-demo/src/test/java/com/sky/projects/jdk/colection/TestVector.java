package com.sky.projects.jdk.colection;

import java.util.Vector;

import org.junit.Test;

public class TestVector {

	private Vector<String> vector = new Vector<String>();

	{
		vector.add("A");
		vector.add("B");
		vector.add("C");
	}

	@Test
	public void testAddString() {
		System.out.println(vector);

		String d = "D";
		vector.add(d);
		System.out.println(vector);

		// 字符串常量是存储在栈中，代表是新的字符串
		d = "DD";
		System.out.println(vector);
	}

	@Test
	public void testAddPerson() {
		Person p1 = new Person(1L, "zhangsan1");
		Person p2 = new Person(2L, "zhangsan2");
		Person p3 = new Person(3L, "zhangsan3");

		Vector<Person> ps = new Vector<>();

		// 添加的是引用
		ps.add(p1);
		ps.add(p2);
		ps.add(p3);

		System.out.println(ps);

		// 对象改变，集合中的值也改变
		p2.setName("lisi");
		System.out.println(ps);
	}

	@Test
	public void testAddInteger() {
		Vector<Integer> ps = new Vector<>();

		ps.add(1111);
		ps.add(2111);
		ps.add(3111);

		Integer i4 = 4111;
		ps.add(i4);
		System.out.println(ps);

		// Integer 存储在栈中
		i4 = 5111;
		System.out.println(ps);
	}

	@Test
	public void testAddLong() {
		Vector<Long> ps = new Vector<>();

		ps.add(1111111L);
		ps.add(2111111L);
		ps.add(3111111L);

		Long i4 = 4111111L;
		ps.add(i4);
		System.out.println(ps);

		// Integer 存储在栈中
		i4 = 5111111L;
		System.out.println(ps);

		Long i5 = new Long(55555555);
		ps.add(i5);
		System.out.println(ps);

		i5 = new Long(321312312);
		System.out.println(ps);
	}

	@Test
	public void testAddDouble() {
		Vector<Double> ps = new Vector<>();

		ps.add(1111111.0);
		ps.add(2111111.0);
		ps.add(3111111.0);

		Double i4 = 4111111.0;
		ps.add(i4);
		System.out.println(ps);

		// Integer 存储在栈中
		i4 = 5111111.0;
		System.out.println(ps);
	}

	@Test
	public void testInteger() {
		// 自动装箱 ： -128 ~ 127 范围内如果存在了一个值，再创建相同值的时候就不会重新创建，而是引用原来那个
		Integer i1 = 100, i2 = 100;
		System.out.println(i1 == i2);

		// 超过byte范围还是会新建对象
		Integer i3 = 188, i4 = 188;
		System.out.println(i3 == i4);
	}

	@Test
	public void testLong() {
		// 自动装箱 ：-128 ~ 127 范围内如果存在了一个值，再创建相同值的时候就不会重新创建，而是引用原来那个
		Long i1 = 100L, i2 = 100L;
		System.out.println(i1 == i2);

		// 超过byte范围还是会新建对象
		Long i3 = 188L, i4 = 188L;
		System.out.println(i3 == i4);
	}
}
