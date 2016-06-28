package com.sky.projects.jdk.util;

import java.util.TreeMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * TreeMap<K, V> 基于红黑树(Red-Black tree_的 NavigableMap 实现。
 * 
 * 该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。
 * 
 * @author zt
 */
public class TreeMapTest extends TestCase {

	TreeMap<String, String> stringMap = new TreeMap<>();

	public TreeMapTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(TreeMapTest.class);
	}

	public void testStringMap() {
		stringMap.put("12", "12");
		stringMap.put("1", "1");
		stringMap.put("12", "12");
		stringMap.put("22", "22");
		stringMap.put("23", "23");
		stringMap.put("213", "213");
		stringMap.put("123", "123");

		System.out.println(stringMap);
	}
}
