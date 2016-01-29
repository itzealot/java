package com.zt.test.collection;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class TestMultiset {
	private Multiset<String> set = null;

	{
		set = HashMultiset.create();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("a");
		set.add("a");
		set.add("b");
	}

	@Test
	public void testCount() {

		// 输出次数
		System.out.println("a : " + set.count("a"));
		System.out.println("b : " + set.count("b"));
		System.out.println("c : " + set.count("c"));
		System.out.println("d : " + set.count("d"));
	}

	@Test
	public void slementSet() {
		Set<String> strings = set.elementSet();
		System.out.println(strings);
	}

	@Test
	public void size() {
		// 返回元素个数，包含重复元素
		System.out.println(set.size());
	}
}
