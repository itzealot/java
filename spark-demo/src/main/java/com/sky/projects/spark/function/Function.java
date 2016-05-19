package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * Base interface for functions whose return types do not create special RDDs.
 * PairFunction and DoubleFunction are handled separately, to allow PairRDDs and
 * DoubleRDDs to be constructed when mapping RDDs of other types.
 * 
 * 输入数据 T1 返回结果数据 R 的功能函数
 * 
 * @author zt
 *
 * @param <T1>
 * @param <R>
 */
public interface Function<T1, R> extends Serializable {

	/**
	 * T1 ---> R
	 * 
	 * 输入数据 T1 返回结果数据 R
	 * 
	 * @param v1
	 * @return
	 * @throws Exception
	 */
	public R call(T1 v1) throws Exception;
}
