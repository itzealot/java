package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A three-argument function that takes arguments of type T1, T2 and T3 and
 * returns an R.
 * 
 * 根据输入数据 T1,T2, T3，返回结果数据 R 的功能函数
 * 
 * @author zt
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <R>
 */
public interface Function3<T1, T2, T3, R> extends Serializable {

	/**
	 * (T1, T2, T3) ---> R
	 * 
	 * 输入数据 T1, T2, T3，返回结果数据 R
	 * 
	 * @param v1
	 * @param v2
	 * @param v3
	 * @return
	 * @throws Exception
	 */
	public R call(T1 v1, T2 v2, T3 v3) throws Exception;
}
