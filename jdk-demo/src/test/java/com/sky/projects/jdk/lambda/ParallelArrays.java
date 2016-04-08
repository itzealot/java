package com.sky.projects.jdk.lambda;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

/**
 * 并行（parallel）数组
 * 
 * Java 8增加了大量的新方法来对数组进行并行处理。 可以说，最重要的是parallelSort()方法，因为它可以在多核机器上极大提高数组排序的速度。
 * 
 * @author zt
 *
 */
public class ParallelArrays {
	/***
	 * 使用了parallelSetAll()方法来对一个有20000个元素的数组进行随机赋值。然后，调用parallelSort方法。
	 * 这个程序首先打印出前10个元素的值，之后对整个数组排序。
	 */
	@Test
	public void test() {
		long[] arrayOfLong = new long[20000];

		Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();

		Arrays.parallelSort(arrayOfLong);
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
		System.out.println();
	}
}