package com.sky.projects.web.common;

import com.sky.projects.web.common.mvc.HtmlStringUtil;

import junit.framework.TestCase;

public class TestHtmlStringUtil extends TestCase {

	public void testFilter() {
		System.out.println(HtmlStringUtil.filter("<a>哈哈</a>"));
	}
}
