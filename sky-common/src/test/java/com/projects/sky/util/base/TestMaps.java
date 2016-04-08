package com.projects.sky.util.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class TestMaps {

	private static Map<Integer, Integer> map = null;

	@Before
	public void before() {
		map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(4, 4);
		map.put(5, 5);
	}

	@After
	public void after() {
		map = null;
	}

	@Test
	public void testGetValues() {
		System.out.println(Maps.values(map));
	}

	@Test
	public void testGetValueByKey() {
		System.out.println(Maps.get(map, new Integer(4)));
	}

	@Test
	public void testIteratorMap() {
		System.out.println(Maps.iteratorMap(map));
	}

	@Test
	public void testEntryFEachMap() {
		System.out.println(Maps.entryFEachMap(map));
	}

	@Test
	public void testKeySetIteratorMap() {
		System.out.println(Maps.keySetIteratorMap(map));
	}

	@Test
	public void testKeySetFEachMap() {
		System.out.println(Maps.keySetFEachMap(map));
	}
}