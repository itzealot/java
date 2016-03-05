package com.zt.projects.sky.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * List, Set, Map convert
 * 
 * @author zengtao
 *
 */
public class ConvertorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testList2Array();
		testArray2List();
		testSet2List();
		testList2Set();
		testSet2Array();
		testArray2Set();
		testMap2Set();
		testMap2List();
	}

	/**
	 * To convert map to list
	 */
	private static void testMap2List() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "ABC");
		map.put("K", "KK");
		map.put("L", "LV");

		// 将Map Key 转化为List
		List<String> mapKeyList = new ArrayList<String>(map.keySet());
		System.out.println("mapKeyList:" + mapKeyList);

		// 将Map Values 转化为List
		List<String> mapValuesList = new ArrayList<String>(map.values());
		System.out.println("mapValuesList:" + mapValuesList);

	}

	/**
	 * To convert map to set
	 */
	private static void testMap2Set() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "ABC");
		map.put("K", "KK");
		map.put("L", "LV");

		// 将Map 的Key 转化为Set
		Set<String> mapKeySet = map.keySet();
		System.out.println("mapKeySet:" + mapKeySet);

		// 将Map 的值Value 化为Set
		Set<String> mapValuesSet = new HashSet<String>(map.values());
		System.out.println("mapValuesSet:" + mapValuesSet);
	}

	/**
	 * To convert array to set
	 */
	private static void testArray2Set() {

		String[] arr = { "AA", "BB", "DD", "CC", "BB" };

		// 数组-->Set, 先转换为List
		Set<String> set = new HashSet<String>(Arrays.asList(arr));
		System.out.println(set);
	}

	/**
	 * To convert set to array
	 */
	private static void testSet2Array() {
		Set<String> set = new HashSet<String>();
		set.add("AA");
		set.add("BB");
		set.add("CC");

		String[] arr = new String[set.size()];
		// Set-->数组
		set.toArray(arr);

		// array to String and display
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * To convert list to set
	 */
	private static void testList2Set() {

		List<String> list = new ArrayList<String>();
		list.add("ABC");
		list.add("EFG");
		list.add("LMN");
		list.add("LMN");

		// List-->Set
		Set<String> listSet = new HashSet<String>(list);
		System.out.println(listSet);
	}

	/**
	 * To convert set to list
	 */
	private static void testSet2List() {

		Set<String> set = new HashSet<String>();
		set.add("AA");
		set.add("BB");
		set.add("CC");

		// Set --> List
		List<String> setList = new ArrayList<String>(set);
		System.out.println(setList);
	}

	/**
	 * To convert list to array
	 */
	private static void testList2Array() {
		// List-->数组
		List<String> list = new ArrayList<String>();
		list.add("AA");
		list.add("BB");
		list.add("CC");
		Object[] objects = list.toArray();// 返回Object数组
		System.out.println("objects:" + Arrays.toString(objects));

		String[] arr = new String[list.size()];
		list.toArray(arr);// 将转化后的数组放入已经创建好的对象中
		System.out.println("strings1:" + Arrays.toString(arr));
	}

	/**
	 * To convert array to list
	 */
	private static void testArray2List() {
		// 数组-->List
		String[] ss = { "JJ", "KK" };
		List<String> list1 = Arrays.asList(ss);
		List<String> list2 = Arrays.asList("AAA", "BBB");
		System.out.println(list1);
		System.out.println(list2);
	}

}