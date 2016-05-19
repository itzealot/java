package com.sky.projects.spark.function;

import java.io.Serializable;

/**
 * A function with no return value.
 * 
 * 输入数据 T 只处理但不返回任何数据的功能函数
 * 
 * @author zt
 *
 * @param <T>
 */
public interface VoidFunction<T> extends Serializable {

	/**
	 * T ---> Void
	 * 
	 * 输入数据 T ，只处理但不返回任何数据
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void call(T t) throws Exception;
}
