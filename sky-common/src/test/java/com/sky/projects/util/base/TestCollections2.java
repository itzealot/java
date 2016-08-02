package com.sky.projects.util.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;

public class TestCollections2 {
	private static Collection<Integer> collection;
	private static Collection<Integer> collection2;

	@Before
	public void before() {
		collection = new ArrayList<Integer>();
		collection.add(new Integer(1));
		collection.add(new Integer(2));
		collection.add(new Integer(3));
		collection.add(new Integer(4));

		collection2 = new HashSet<Integer>();
		collection2.add(1);
		collection2.add(4);
		collection2.add(3);
		collection2.add(2);
		collection2.add(3);

	}

	@After
	public void after() {
		collection = null;
		collection2 = null;
	}
}
