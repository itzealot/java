package com.zt.test;

import org.junit.Test;

public class TestHtmlStringUtil {

	@Test
	public void testFilter() {
		System.out.println(HtmlStringUtil.filter("<a>哈哈</a>"));
	}
}
