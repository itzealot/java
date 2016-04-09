package com.sky.projects.guava.collection;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;

/**
 * 1. 不可变集合 ImmutableSortedSet.<br />
 * 
 * 2. 不可变对象有很多优点，包括： <br />
 * ---1). 当对象被不可信的库调用时，不可变形式是安全的.<br />
 * ---2). 不可变对象被多个线程调用时，不存在竞态条件问题.<br />
 * ---3). 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率(分析和测试细节).<br />
 * ---4). 不可变对象因为有固定不变，可以作为常量来安全使用.<br />
 * 
 * 3. 创建对象的不可变拷贝是一项很好的防御性编程技巧。Guava为所有JDK标准集合类型和Guava新集合类型都提供了简单易用的不可变版本.<br />
 * 
 * 
 * 4. 创建方式<br />
 * ---1). copyOf方法，如ImmutableSet.copyOf(set);<br />
 * ---2). of方法，如ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);<br />
 * ---3). Builder工具<br />
 * 
 * Title: TestImmutableSet.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月14日
 */
public class TestImmutableSet {

	@Test
	public void testOf() {

		// 通过其静态方法 of 来构造 ImmutableSortedSet 对象，且自动去重和排序(Set 的变种)
		ImmutableSortedSet<String> set = ImmutableSortedSet.of("a", "b", "c",
				"a", "d", "b");
		System.out.println(set.toString());
	}

	@Test
	public void testCopyOf() {

		// 通过其静态方法 copyOf 来构造 ImmutableSortedSet 对象，且自动去重和排序(Set 的变种)
		String[] lists = { "b", "c", "d", "b", "a" };
		ImmutableSortedSet<String> copySet = ImmutableSortedSet.copyOf(lists);
		System.out.println(copySet.toString());
	}

	@Test
	public void testAsList() {
		ImmutableSortedSet<String> set = ImmutableSortedSet.of("a", "b", "c",
				"a", "d", "b");
		ImmutableList<String> strings = set.asList();
		System.out.println(strings);
	}
}
