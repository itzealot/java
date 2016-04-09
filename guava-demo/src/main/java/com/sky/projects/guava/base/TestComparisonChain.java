package com.sky.projects.guava.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.sky.projects.guava.entity.User;

public class TestComparisonChain {
	List<User> users = new ArrayList<User>();

	{
		users.add(new User(2L, "lili", "lili", new Date(), "I am zhangsan", true, false));
		users.add(new User(3L, "zhangsan", "zhangsan", new Date(), "I am zhangsan", true, true));
		users.add(new User(1L, "wangwu", "wangwu", new Date(), "I am wangwu", true, false));
		users.add(new User(2L, "lisi", "lisi", new Date(), "I am lisi", true, false));
		users.add(new User(2L, "lisi", "lisi", new Date(), "I am lisi", true, true));
		users.add(new User(2L, "lisi", "lisi", new Date(), "I am lisi", false, true));
		users.add(new User(2L, "lisi", "lisi", new Date(), "I am lisi", false, false));
	}

	@Test
	public void testCompare() {
		Collections.sort(users);
		display();
	}

	public void display() {
		for (User user : users) {
			System.out.println(user);
		}
	}
}
