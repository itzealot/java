package com.sky.projects.jdk.util;

import java.util.Set;
import java.util.TreeSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * TreeSet<T> --> 有序的 Set
 * 
 * * TreeSet<K> ---> TreeMap<K, Object>(value is same)
 * 
 * HastSet<K> ---> HashMap<K, Object>(value is same)
 * 
 * 使用元素的自然顺序对元素进行排序，或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。
 * 
 * @author zt
 */
public class TreeSetTest extends TestCase {

	private Set<Integer> set = new TreeSet<>();
	private Set<String> strings = new TreeSet<>();

	public TreeSetTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TreeSetTest.class);
	}

	public void testInteger() {
		set.add(1);
		set.add(3);
		set.add(2);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(3);
		System.out.println(set);
	}

	public void testString() {
		strings.add("b");
		strings.add("a");
		strings.add("A");
		strings.add("ac");
		strings.add("ac");
		strings.add("aa");
		strings.add("c");
		strings.add("A");
		strings.add("Av");
		strings.add("bv");
		strings.add("B");
		System.out.println(strings);
	}
}
