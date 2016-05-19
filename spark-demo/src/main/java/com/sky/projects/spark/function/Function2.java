package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A two-argument function that takes arguments of type T1 and T2 and returns an
 * R.
 * 
 * 输入数据 T1, T2，返回结果数据 R 的功能函数
 * 
 * @author zt
 *
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public interface Function2<T1, T2, R> extends Serializable {

	/**
	 * (T1, T2) ---> R
	 * 
	 * 输入数据 T1, T2，返回结果数据 R
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @throws Exception
	 */
	public R call(T1 v1, T2 v2) throws Exception;
}
