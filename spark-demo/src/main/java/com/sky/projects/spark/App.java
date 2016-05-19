package com.sky.projects.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * This program just counts the number of lines containing ‘a’ and the number
 * containing ‘b’ in a text file. Note that you’ll need to replace
 * YOUR_SPARK_HOME with the location where Spark is installed
 * 
 * 统计一行中含有字母 a 的数量与统计一行中含有字母 b 的数量
 * 
 * @author zt
 *
 */
public class App {

	public static final String master = "spark://172.20.129.136:7077";

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		String logFile = "YOUR_SPARK_HOME/README.md";

		SparkConf conf = new SparkConf().setAppName("CountApplication");
		JavaSparkContext sc = new JavaSparkContext(conf);

		// 创建 JavaRDD
		JavaRDD<String> logData = sc.textFile(logFile).cache();

		/*
		 * 统计含字符 a
		 */
		long numAs = logData.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String s) {
				return s.contains("a");
			}
		}).count();

		/*
		 * 统计含字符 b
		 */
		long numBs = logData.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String s) {
				return s.contains("b");
			}
		}).count();

		System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

		sc.close();
	}
}