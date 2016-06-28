package com.sky.projects.jdk.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 封装了对应所有的数据结构转为线程安全的数据结构的方法
 * 
 * @author zt
 */
public class CollectionsTest extends TestCase {
	public CollectionsTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(CollectionsTest.class);
	}

	public void testApp() {
		assertTrue(true);

		// 获取线程安全的 Collection<T>
		Collection<String> synCollection = Collections.synchronizedCollection(new ArrayList<>());
		assertNotNull("synCollection is null", synCollection);

		// 获取线程安全的 List<T>
		List<String> synList = Collections.synchronizedList(new ArrayList<>());
		assertNotNull("synList is null", synList);

		// 获取线程安全的 Map<K, V>
		Map<String, Object> synMap = Collections.synchronizedMap(new HashMap<>());
		assertNotNull("synMap is null", synMap);

		// 获取线程安全的 Set<T>
		Set<String> synSet = Collections.synchronizedSet(new HashSet<>());
		assertNotNull("synSet is null", synSet);

		// 获取线程安全的 SortedMap<K, V>
		SortedMap<String, Object> synSortedMap = Collections.synchronizedSortedMap(new TreeMap<>());
		assertNotNull("synSortedMap is null", synSortedMap);

		// 获取线程安全的 SortedSet<T>
		SortedSet<String> synSortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
		assertNotNull("synSortedSet is null", synSortedSet);
	}
}