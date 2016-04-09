package com.sky.projects.jdk.colection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestMapComparator {
	@Test
	public void test() {

		List<Map<String, String>> listResult = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new LinkedHashMap<String, String>();
		map1.put("count", "2");
		map1.put("name", "abc");
		map1.put("key", "acv");
		listResult.add(map1);

		Map<String, String> map2 = new LinkedHashMap<String, String>();
		map2.put("count", "3");
		map2.put("name", "bbc");
		map2.put("key", "bcv");
		listResult.add(map2);

		Map<String, String> map3 = new LinkedHashMap<String, String>();
		map3.put("count", "1");
		map3.put("name", "cbc");
		map3.put("key", "ccv");
		listResult.add(map3);

		Map<String, String> map4 = new LinkedHashMap<String, String>();
		map4.put("count", "4");
		map4.put("name", "cbc");
		map4.put("key", "ccv");
		listResult.add(map4);

		System.out.println("排序前：");
		for (Map<String, String> map : listResult) {
			System.out.println("count:" + map.get("count") + " name:" + map.get("name") + " key:" + map.get("key"));
		}
		Collections.sort(listResult, new MapComparator<String, String>("count", false));

		System.out.println("降序：");
		for (Map<String, String> map : listResult) {
			System.out.println("count:" + map.get("count") + " name:" + map.get("name") + " key:" + map.get("key"));
		}
		Collections.sort(listResult, new MapComparator<String, String>("count", true));

		System.out.println("升序：");
		for (Map<String, String> map : listResult) {
			System.out.println("count:" + map.get("count") + " name:" + map.get("name") + " key:" + map.get("key"));
		}
	}
}
