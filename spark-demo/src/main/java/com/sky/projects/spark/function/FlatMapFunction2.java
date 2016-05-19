package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A function that takes two inputs and returns zero or more output records.
 * 
 * 输入数据 T1, T2 返回多个结果数据 R 的功能函数
 * 
 * @author zt
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public interface FlatMapFunction2<T1, T2, R> extends Serializable {

	/**
	 * (T1, T2) ---> Iterable<R>
	 * 
	 * 输入数据 T1, T2 返回多个结果数据 R
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 * @throws Exception
	 */
	public Iterable<R> call(T1 t1, T2 t2) throws Exception;
}
