package com.zt.test;

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
 * 
 * @author zt
 *
 */
public class SimpleApp {

	public static void main(String[] args) {
		String logFile = "YOUR_SPARK_HOME/README.md";

		SparkConf conf = new SparkConf().setAppName("Simple Application");
		JavaSparkContext sc = new JavaSparkContext(conf);

		JavaRDD<String> logData = sc.textFile(logFile).cache();

		// 统计含字符 a
		Function<String, Boolean> funA = new Function<String, Boolean>() {
			private static final long serialVersionUID = -8530970732718222038L;

			public Boolean call(String s) {
				return s.contains("a");
			}
		};

		long numAs = logData.filter(funA).count();

		// 统计 含字符 b
		Function<String, Boolean> funB = new Function<String, Boolean>() {
			private static final long serialVersionUID = 507354263131745742L;

			public Boolean call(String s) {
				return s.contains("b");
			}
		};

		long numBs = logData.filter(funB).count();

		System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

		sc.close();
	}
}