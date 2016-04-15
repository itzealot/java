package com.projects.sky.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.projects.sky.util.ref.Sorts;
import com.projects.sky.util.ref.Sorts.SortType;

public class SortsTest {
	private List<User> users = new ArrayList<>();

	{
		users.add(new User(1L, "zhangsan", "zhangsan", new Date(), 10, "I am zhangsan.", 2));
		users.add(new User(2L, "zhangsan2", "zhangsan2", new Date(), 12, "I am zhangsan.", 3));
		users.add(new User(3L, "zhangsan3", "zhangsan2", new Date(), 12, "I am zhangsan.", 3));
		users.add(new User(4L, "zhangsan2", "zhangsan2", new Date(), 8, "I am zhangsan.", 4));
		users.add(new User(5L, "zhangsan3", "zhangsan3", new Date(), 9, "I am zhangsan.", 2));
		users.add(new User(6L, "zhangsan4", "zhangsan4", new Date(), 6, "I am zhangsan.", 6));
		users.add(new User(7L, "zhangsan5", "zhangsan5", new Date(), 9, "I am zhangsan.", 2));
	}

	@Test
	public void testSortNameASC() {
		sort("name", "asc");
	}

	@Test
	public void testSortNameDESC() {
		sort("name", "desc");
	}

	@Test
	public void testSortAgeDESC() {
		sort("age", SortType.DESC);
	}

	@Test
	public void testSortAgeASC() {
		sort("age", SortType.ASC);
	}

	@Test
	public void testSortCountASCByCollection() {
		sortByCollection("count", SortType.ASC);
	}

	@Test
	public void testSortCountDescByCollection() {
		sortByCollection("count", "desc");
	}

	private void sort(String name, String sortType) {
		Sorts.sort(users, name, sortType);
		for (User user : users) {
			System.out.println(user);
		}
	}

	private void sort(String name, SortType sortType) {
		Sorts.sort(users, name, sortType);
		for (User user : users) {
			System.out.println(user);
		}
	}

	private void sortByCollection(String name, SortType sortType) {
		Sorts.sort(users, name, sortType);
		for (User user : users) {
			System.out.println(user);
		}
	}

	private void sortByCollection(String name, String sortType) {
		Sorts.sort(users, name, sortType);
		for (User user : users) {
			System.out.println(user);
		}
	}
}
