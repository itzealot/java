package com.sky.projects.web.common;

import org.junit.Test;

import com.sky.projects.web.common.mvc.HtmlStringUtil;

public class TestHtmlStringUtil {

	@Test
	public void testFilter() {
		System.out.println(HtmlStringUtil.filter("<a>哈哈</a>"));
	}
}
