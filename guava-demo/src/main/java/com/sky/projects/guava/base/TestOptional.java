package com.sky.projects.guava.base;

import org.junit.Test;

import com.google.common.base.Optional;

/**
 * Test Optional<T>
 * 
 * Title: TestOptional.<br />
 * Description: 1. 用Optional<T>表示可能为null的T类型引用。<br />
 * 2. 一个Optional实例可能包含非null的引用（我们称之为引用存在）， 也可能什么也不包括（称之为引用缺失）。<br />
 * 3. 它从不说包含的是null值，而是用存在或缺失来表示。但Optional从不会包含null值引用 <br />
 * 
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月8日
 */
public class TestOptional {
	private Optional<Integer> possible = null;

	/**
	 * Optional.of(T) : 创建指定引用的Optional实例，若引用为null则快速失败
	 * 
	 * Title: test.<br />
	 * Description: .<br />
	 */
	@Test
	public void testOf() {
		// 创建指定引用的Optional实例，若引用为null则快速失败
		possible = Optional.of(5);

		// 是否存在: true
		System.out.println("isPresent : " + possible.isPresent());

		// 引用值 : 5
		System.out.println(possible.get());
	}

	/**
	 * Optional.absent() : 创建引用缺失的Optional实例
	 * 
	 * Title: testAbsent.<br />
	 * Description: .<br />
	 */
	@Test
	public void testAbsent() {
		// 创建引用缺失的Optional实例
		possible = Optional.absent();

		// 是否存在: false
		System.out.println(possible.isPresent());

		// 引用值获取异常 即 possible.get() 抛出异常
	}

	/**
	 * Optional.fromNullable(T) : 创建指定引用的Optional实例，若引用为null则表示缺失
	 * 
	 * Title: testFfomNullable.<br />
	 * Description: .<br />
	 */
	@Test
	public void testFfomNullable() {
		// 上面两种方式的综合,创建指定引用的Optional实例，若引用为null则表示缺失
		possible = Optional.fromNullable(6);
		System.out.println(possible.isPresent());
	}
}
