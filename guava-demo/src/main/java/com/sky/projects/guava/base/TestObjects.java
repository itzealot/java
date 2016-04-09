package com.sky.projects.guava.base;

import org.junit.Test;

import com.google.common.base.Objects;

/**
 * 
 * 1. 当一个对象中的字段可以为null时，实现Object.equals方法会很痛苦，因为不得不分别对它们进行null检查。<br />
 * 2. 使用Objects.equal帮助你执行 null 敏感的equals判断，从而避免抛出 NullPointerException。
 * 
 * Title: TestObjects.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月8日
 */
public class TestObjects {

	@Test
	public void testEqual() {
		// returns true
		System.out.println(Objects.equal("a", "a"));

		// returns false
		System.out.println(Objects.equal(null, "a"));

		// returns false
		System.out.println(Objects.equal("a", null));

		// returns true
		System.out.println(Objects.equal(null, null));
	}

	@Test
	public void testHashCode() {

		/**
		 * 1. Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。<br />
		 * 2. 你可以使用Objects.hashCode(field1,field2, …, fieldn)来代替手动计算散列值。
		 */
		System.out.println(Objects.hashCode("1", 1L, 2));
	}

	@Test
	@Deprecated
	public void test2String() {
		// Object's toStringHelper method was deprecated
	}

	@Test
	@Deprecated
	public void testFirstNonNull() {
		// Object's firstNonNull method was deprecated
	}
}
