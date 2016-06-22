package com.sky.projects.jdk.thread;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sky.projects.jdk.thread.demo1.FileSearchUtil;

public class TestFileSearchUtil {

	@Test
	public void testSearchByKeywords() {
		List<String> lists = new ArrayList<String>();
		FileSearchUtil.searchByKeywords(lists, "算法", "D:\\C++Practice");

		// 遍历输出
		for (String s : lists) {
			System.out.println(s);
		}
	}
}