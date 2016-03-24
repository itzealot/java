package com.projects.sky.file;

import org.junit.Test;

public class TestFileEncodeUtil {
	@Test
	public void testGetFileCharset() {
		System.out.println(FileEncodeUtil.getFileCharset("E:/test.txt"));
	}
}
