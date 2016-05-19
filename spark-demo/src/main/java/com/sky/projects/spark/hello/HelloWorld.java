package com.sky.projects.spark.hello;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class HelloWorld {

	public static final String master = "spark://master:7077";

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("demo").setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);

		System.out.println(sc);
		sc.stop();
	}
}
