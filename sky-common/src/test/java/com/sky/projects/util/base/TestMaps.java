package com.sky.projects.util.base;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sky.projects.util.base.Maps;

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

}
