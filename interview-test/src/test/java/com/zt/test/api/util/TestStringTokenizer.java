package com.zt.test.api.util;

import java.util.StringTokenizer;

import org.junit.Test;

/**
 * StringTokenizer是一个很方便的字符串分解器.<br />
 * 1. 第一个参数就是要分隔的String<br />
 * 2. 第二个是分隔字符集合<br />
 * 3. 第三个参数表示分隔符号是否作为标记返回，如果不指定分隔字符，默认的是："\t\n\r\f".<br />
 * 
 * @author zengtao
 *
 */
public class TestStringTokenizer {

	/**
	 * To display StringTokenizer tokenizer
	 * 
	 * @param tokenizer
	 */
	public void display(StringTokenizer tokenizer) {
		// To for each
		while (tokenizer.hasMoreElements()) {
			System.out.println(tokenizer.nextToken());
		}
	}

	/**
	 * 测试StringTokenizer(String source) constructor.<br />
	 */
	@Test
	public void testStringTokenizer1() {
		String str = "hello.Today\"I am \" going to my home town";
		/**
		 * StringTokenizer(String str) using 分隔字符，默认的是："\t\n\r\f"
		 */
		StringTokenizer tokenizer = new StringTokenizer(str);
		/**
		 * Result:<br />
		 * 1.hello.Today"I.<br />
		 * am.<br />
		 * ".<br />
		 * going.<br />
		 * to.<br />
		 * my.<br />
		 * home.<br />
		 * town.<br />
		 */
		display(tokenizer);
	}

	/**
	 * 测试StringTokenizer(String source, String delim) constructor.<br />
	 */
	@Test
	public void testStringTokenizer2() {
		String str = "book, author, publication,,,date published";
		/**
		 * StringTokenizer(String str) using 分隔字符 ","
		 */
		StringTokenizer tokenizer = new StringTokenizer(str, ",");
		/**
		 * Result: <br />
		 * book.<br />
		 * author.<br />
		 * publication.<br />
		 * date published.<br />
		 */
		display(tokenizer);
	}

	/**
	 * 测试StringTokenizer(String source, String delim, boolean flag) constructor.<br />
	 */
	@Test
	public void testStringTokenizer3() {
		String str = "book, author, publication,,,date published";
		/**
		 * StringTokenizer(String str) using 分隔字符 ",".<br />
		 * param 3 : flag is true, 分隔符也作为标记返回
		 */
		StringTokenizer tokenizer = new StringTokenizer(str, ",", true);
		/**
		 * Result: <br />
		 * book.<br />
		 * author.<br />
		 * publication.<br />
		 * date published.<br />
		 */
		display(tokenizer);
	}
}
