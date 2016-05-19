package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A function that returns zero or more output records from each input record.
 * 
 * 输入数据 T 返回多个结果数据 R 的功能函数
 * 
 * @author zt
 *
 * @param <T>
 * @param <R>
 */
public interface FlatMapFunction<T, R> extends Serializable {
	/**
	 * T ---> Iterable<R>
	 * 
	 * 输入数据 T 返回多个结果数据 R
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Iterable<R> call(T t) throws Exception;
}
