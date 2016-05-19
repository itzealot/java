package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A function that returns Doubles, and can be used to construct DoubleRDDs.
 * 
 * 输入数据 T 返回单个 double 数据的功能函数
 * 
 * @author zt
 *
 * @param <T>
 */
public interface DoubleFunction<T> extends Serializable {

	/**
	 * T ---> double
	 * 
	 * 输入数据 T 返回单个 double 数据
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public double call(T t) throws Exception;
}
