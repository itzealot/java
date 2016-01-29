package com.zt.test.file;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zt.test.file.FileSearchUtil;

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
