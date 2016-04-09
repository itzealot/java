package com.sky.projects.guava.string;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.base.Joiner;

/**
 * 连接器[Joiner]
 * 
 * Title: TestJoin.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月20日
 */
public class TestJoin {

	@Test
	public void testOn() {
		// 使用 "; " 连接并跳过 null
		Joiner joiner = Joiner.on("; ").skipNulls();
		System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
	}

	@Test
	public void testUseFoNull() {
		// 使用 "; " 连接并使用"null" 代替 null，否则将会出现 NullPointerException
		Joiner joiner = Joiner.on("; ").useForNull("null");
		System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
	}

	@Test
	public void testJoin() {
		// Joiner也可以用来连接对象类型，在这种情况下，它会把对象的toString()值连接起来
		System.out.println(Joiner.on(", ").join(Arrays.asList(1, 5, 7)));
	}
}
