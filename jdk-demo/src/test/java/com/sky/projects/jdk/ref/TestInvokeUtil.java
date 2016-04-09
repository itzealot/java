package com.sky.projects.jdk.ref;

import org.junit.Test;

public class TestInvokeUtil {

	private String className = "com.zt.test.HtmlStringUtil";
	private String methodName = "filter";
	private String[] messages = { "<a>哈哈</a>" };

	@Test
	public void testExecute() {
		Object result = (String) ClassUtil.execute(className, methodName, messages, String.class);

		System.out.println(result);
	}
}
