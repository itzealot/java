package com.zt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCollectionUtil {
	private static Collection<Integer> collection;
	private static Collection<Integer> collection2;

	@Before
	public void before() {
		collection = new ArrayList<Integer>();
		collection.add(new Integer(1));
		collection.add(new Integer(2));
		collection.add(new Integer(3));
		collection.add(new Integer(4));

		// Will sort when you add object and not add the same object
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

	@Test
	public void testGetStringFromCollection() {
		System.out.println(CollectionUtil.getStringFromCollection(collection,
				", ", false));

		System.out.println(CollectionUtil.getStringFromCollection(collection2,
				", ", false));

	}
}
