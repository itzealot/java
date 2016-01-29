package com.zt.util;

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
public class TestMapUtil {

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
	public void test_() {

	}

	@Test
	public void testGetValues() {
		System.out.println(MapUtil.getValues(map));
	}

	@Test
	public void testIsValidateMap() {
		String str = MapUtil.isValidateMap(map) ? "It's validate"
				: "It's not validate";
		System.out.println(str);
	}

	@Test
	public void testIsValidateKey() {
		System.out.println("3 is key: "
				+ MapUtil.isValidateKey(map, new Integer(3)));
		System.out.println("7 is key: "
				+ MapUtil.isValidateKey(map, new Integer(7)));
	}

	@Test
	public void testGetValueByKey() {
		System.out.println(MapUtil.getValueByKey(map, new Integer(4)));
	}

	@Test
	public void testIteratorMap() {
		System.out.println(MapUtil.iteratorMap(map));
	}

	@Test
	public void testEntryFEachMap() {
		System.out.println(MapUtil.entryFEachMap(map));
	}

	@Test
	public void testKeySetIteratorMap() {
		System.out.println(MapUtil.keySetIteratorMap(map));
	}

	@Test
	public void testKeySetFEachMap() {
		System.out.println(MapUtil.keySetFEachMap(map));
	}
}
