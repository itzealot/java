package com.sky.projects.spark.function;

import java.io.Serializable;

import scala.Tuple2;

/**
 * A function that returns key-value pairs (Tuple2&lt;K, V&gt;), and can be used
 * to construct PairRDDs.
 * 
 * 将输入数据 T 转换为元组数据 Tuple2<K, V> 的功能函数
 * 
 * @author zt
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public interface PairFunction<T, K, V> extends Serializable {

	/**
	 * T ---> Tuple2<K, V>
	 * 
	 * 输入数据 T 转换为 元组数据 Tuple2<K, V>
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Tuple2<K, V> call(T t) throws Exception;
}
