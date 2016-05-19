package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A function that returns zero or more records of type Double from each input
 * record.
 * 
 * 输入数据 T 返回多个 Double 数据的功能函数
 * 
 * @author zt
 *
 * @param <T>
 */
public interface DoubleFlatMapFunction<T> extends Serializable {

	/**
	 * T ---> Iterable<Double>
	 * 
	 * 输入数据 T 返回多个 Double 数据
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Iterable<Double> call(T t) throws Exception;
}
