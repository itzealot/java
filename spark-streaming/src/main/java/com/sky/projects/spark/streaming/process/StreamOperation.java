package com.sky.projects.spark.streaming.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;

import scala.Tuple2;

@SuppressWarnings("serial")
public class StreamOperation implements Serializable {

	/**
	 * map
	 * 
	 * @param stream
	 * @return
	 */
	public <T> JavaDStream<T> map(JavaDStream<T> stream) {
		return stream.map(new Function<T, T>() {
			@Override
			public T call(T t) throws Exception {
				// TODO According the old Object to create new Object and return
				return t;
			}

		});
	}

	/**
	 * filter
	 * 
	 * @param stream
	 * @return
	 */
	public <T> JavaDStream<T> filter(JavaDStream<T> stream) {
		return stream.filter(new Function<T, Boolean>() {
			@Override
			public Boolean call(T t) throws Exception {
				// TODO according the object value to return true or false
				return t != null;
			}

		});
	}

	/**
	 * flatMap
	 * 
	 * @param stream
	 * @return
	 */
	public <T> JavaDStream<T[]> flatMap(JavaDStream<T> stream) {
		return stream.flatMap(new FlatMapFunction<T, T[]>() {
			@Override
			public Iterable<T[]> call(T t) throws Exception {
				// TODO split the object to array
				return null;
			}
		});
	}

	/**
	 * mapToPair
	 * 
	 * @param stream
	 * @return
	 */
	public <T> JavaPairDStream<String, T> mapToPair(JavaDStream<T> stream) {
		return stream.mapToPair(new PairFunction<T, String, T>() {
			@Override
			public Tuple2<String, T> call(T t) throws Exception {
				// TODO create the reduce key base on object
				return new Tuple2<String, T>("reduce-key", t);
			}

		});
	}

	/**
	 * reduceByKey
	 * 
	 * @param stream
	 * @return
	 */
	public <T> JavaPairDStream<String, T> reduceByKey(JavaPairDStream<String, T> stream) {
		return stream.reduceByKey(new Function2<T, T, T>() {
			@Override
			public T call(T v1, T v2) throws Exception {
				// TODO select the object base on the two objects
				return v1 == null ? v2 : v1;
			}
		}, 5);
	}

	/**
	 * mapPartitions
	 * 
	 * @param stream
	 * @return
	 */
	public <T> JavaDStream<T> mapPartitions(JavaPairDStream<String, T> stream) {
		return stream.mapPartitions(new FlatMapFunction<Iterator<Tuple2<String, T>>, T>() {
			@Override
			public Iterable<T> call(Iterator<Tuple2<String, T>> t) throws Exception {
				// TODO flat the Pair Stream to Stream when execute reduceByKey
				List<T> lists = new ArrayList<>();
				while (t.hasNext()) {
					// TODO deal the reduce object here
					lists.add(t.next()._2());
				}

				return lists;
			}
		});
	}

	public <T> void foreach(JavaDStream<T> stream) {
		stream.foreachRDD(new Function<JavaRDD<T>, Void>() {
			@Override
			public Void call(JavaRDD<T> rdd) throws Exception {
				// TODO foreach partition
				rdd.foreachPartition(new VoidFunction<Iterator<T>>() {
					@Override
					public void call(Iterator<T> t) throws Exception {
						// TODO To deal with the object array
						while (t.hasNext()) {
							T obj = t.next();

							// TODO To deal with the object
							obj.toString();
						}
					}

				});

				return null;
			}

		});
	}
}
