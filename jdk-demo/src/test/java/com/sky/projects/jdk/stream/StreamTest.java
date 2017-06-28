package com.sky.projects.jdk.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Stream 就如同一个迭代器(Iterator)，单向且不可往复，数据只能遍历一次，遍历过一次后即用尽了
 * 
 * IntStream、LongStream、DoubleStream。当然我们也可以用 Stream<Integer>、Stream<Long>
 * >、Stream<Double>，但是 boxing 和 unboxing 会很耗时，所以特别为这三种基本数值型提供了对应的 Stream。
 * 
 * @author zealot
 *
 */
public class StreamTest {
	List<String> lists = new ArrayList<>();

	{
		for (int i = 0; i < 10; i++)
			lists.add(String.valueOf(i));
	}

	/**
	 * Collection.stream 获取流
	 */
	@Test
	public void testCollectionStream() {
		// 过滤而后遍历输出
		lists.stream().filter(t -> Integer.valueOf(t) % 2 == 0).forEach(t -> System.out.print(t + " "));
		System.out.println();

		// reduce
		System.out.println(lists.stream().reduce((t1, t2) -> {
			return String.valueOf(Integer.parseInt(t1) + Integer.parseInt(t2));
		}).get());

		Object value = "message";
		System.out.println(Optional.of(value).orElse("can't return"));
		value = null;
		System.out.println(Optional.ofNullable(value).orElse("replace null"));
	}

	@Test
	public void testCollectionParallelStream() {
		parallelStreamTimeSpend();
		streamTimeSpend();
	}

	@Test
	public void testOf() {
		Stream.of(1, 2, 3, 4, 5).map(i -> i * 2).filter(i -> i % 3 != 0).forEach(i -> System.out.println(i));
		System.out.println("------------------------");
		String[] arrays = new String[] { "a", "b", "c" };
		Stream.of(arrays).filter(i -> i.hashCode() % 3 != 0).forEach(System.out::println);
	}

	/**
	 * {@link java.util.stream.StreamSupport#stream(java.util.Spliterator, boolean)
	 * 
	 * parallel : true
	 * 
	 * slow
	 */
	private void parallelStreamTimeSpend() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			lists.parallelStream().map(s -> String.valueOf("prefix_" + s)).map(s -> s + "_suffix").forEach(s -> {
			});
		}
		System.out.println("parallelStream spend :" + (System.currentTimeMillis() - start) + " ms");
	}

	/**
	 * {@link java.util.stream.StreamSupport#stream(java.util.Spliterator, boolean)
	 * 
	 * parallel : false
	 * 
	 * quick
	 */
	private void streamTimeSpend() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			lists.stream().map(s -> String.valueOf("prefix_" + s)).map(s -> s + "_suffix").forEach(s -> {
			});
		}
		System.out.println("stream spend :" + (System.currentTimeMillis() - start) + " ms");
	}
}
