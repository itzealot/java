package com.zt.util;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author zengtao 2015-5-15
 *
 */
public class TestSetUtil {

	private static Set<Integer> set = null;

	@Before
	public void before() {
		set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(4);
		set.add(3);
		set.add(5);
		set.add(2);
	}

	@After
	public void after() {
		set = null;
	}

	@Test
	public void testIsValidate() {
		System.out.println("It's validate : " + SetUtil.isValidate(set));
	}

	@Test
	public void testIterator() {
		System.out.println(SetUtil.iterator(set));
	}

}
