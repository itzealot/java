package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A zero-argument function that returns an R.
 * 
 * 无输入数据，返回结果数据 R 的功能函数
 * 
 * @author zt
 *
 * @param <R>
 */
public interface Function0<R> extends Serializable {

	/**
	 * ---> R
	 * 
	 * 无输入数据，返回结果数据 R
	 * 
	 * @return
	 * @throws Exception
	 */
	public R call() throws Exception;
}
