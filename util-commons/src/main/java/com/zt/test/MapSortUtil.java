package com.zt.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MapSortUtil {
	@Test
	public void test() {

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("count", 2);
		map1.put("name", "abc");
		map1.put("key", "acv");
		listResult.add(map1);

		Map<String, Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("count", 3);
		map2.put("name", "bbc");
		map2.put("key", "bcv");
		listResult.add(map2);

		Map<String, Object> map3 = new LinkedHashMap<String, Object>();
		map3.put("count", 1);
		map3.put("name", "cbc");
		map3.put("key", "ccv");
		listResult.add(map3);

		Map<String, Object> map4 = new LinkedHashMap<String, Object>();
		map4.put("count", 4);
		map4.put("name", "cbc");
		map4.put("key", "ccv");
		listResult.add(map4);

		System.out.println("排序前：");
		for (Map<String, Object> map : listResult) {
			System.out.println("count:" + map.get("count") + " name:" + map.get("name") + " key:" + map.get("key"));
		}
		Collections.sort(listResult, new MapComparatorDesc());

		System.out.println("降序：");
		for (Map<String, Object> map : listResult) {
			System.out.println("count:" + map.get("count") + " name:" + map.get("name") + " key:" + map.get("key"));
		}
		Collections.sort(listResult, new MapComparatorAsc());

		System.out.println("升序：");
		for (Map<String, Object> map : listResult) {
			System.out.println("count:" + map.get("count") + " name:" + map.get("name") + " key:" + map.get("key"));
		}
	}

	static class MapComparatorDesc implements Comparator<Map<String, Object>> {
		@Override
		public int compare(Map<String, Object> m1, Map<String, Object> m2) {
			Integer v1 = Integer.valueOf(m1.get("count").toString());
			Integer v2 = Integer.valueOf(m2.get("count").toString());
			if (v2 != null) {
				return v2.compareTo(v1);
			}
			return 0;
		}

	}

	static class MapComparatorAsc implements Comparator<Map<String, Object>> {
		@Override
		public int compare(Map<String, Object> m1, Map<String, Object> m2) {
			Integer v1 = Integer.valueOf(m1.get("count").toString());
			Integer v2 = Integer.valueOf(m2.get("count").toString());
			if (v1 != null) {
				return v1.compareTo(v2);
			}
			return 0;
		}

	}

}

