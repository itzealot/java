package com.sky.projects.spark.function;

import java.io.Serializable;

import scala.Tuple2;

/**
 * A function that returns zero or more key-value pair records from each input
 * record. The key-value pairs are represented as scala.Tuple2 objects.
 * 
 * 输入数据 T 转换为多个元组数据 Tuple2<K, V> 的功能函数
 * 
 * @author zt
 *
 * @param <T>
 * @param <K>
 * @param <V>
 */
public interface PairFlatMapFunction<T, K, V> extends Serializable {

	/**
	 * T ---> Iterable<Tuple2<K, V>>
	 * 
	 * 输入数据 T 返回多个元组数据Tuple2<K, V>
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Iterable<Tuple2<K, V>> call(T t) throws Exception;
}
