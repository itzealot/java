package com.sky.projects.guava.base;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

public class TestMoreObjects {
	/**
	 * 1. 好的toString方法在调试时是无价之宝，但是编写toString方法有时候却很痛苦。<br />
	 * 2. 使用 Objects.toStringHelper可以轻松编写有用的toString方法。<br />
	 * 3. add(String name, int value) 即name=value形式
	 * 
	 * Title: test2String.<br />
	 * Description: .<br />
	 */
	@Test
	public void toStringHelper() {
		// Returns "ClassName{x=1, y=2}"
		System.out.println(MoreObjects.toStringHelper(new Object()).add("x", 1).add("y", 2).toString());

		// Returns "MyObject{x=1}"
		System.out.println(MoreObjects.toStringHelper("MyObject").add("x", 1).toString());
		System.out.println(MoreObjects.toStringHelper(this.getClass().getName()).add("x", 1).toString());
	}

	@Test
	public void firstNonNull() {
		System.out.println(MoreObjects.firstNonNull(null, "sss"));
		System.out.println(MoreObjects.firstNonNull(new Object(), "sss"));
		System.out.println(MoreObjects.firstNonNull(new ArrayList<Date>(), "sss"));
	}
	
	@Test
	public void toStringHelper2() {
		// Object{x=1, 1}
		ToStringHelper  t = MoreObjects.toStringHelper(new Object());
		t.add("x", 1);
		t.addValue(1);
		System.out.println(t);
	}
}
