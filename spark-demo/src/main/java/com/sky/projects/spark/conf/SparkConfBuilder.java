package com.sky.projects.spark.conf;

import org.apache.spark.SparkConf;

/**
 * 
 * 
 * @author zt
 */
public final class SparkConfBuilder {

	public static SparkConf build(String master, String appName) {
		SparkConf sparkConf = new SparkConf();

		sparkConf.setMaster(master).setAppName(appName)
				// 设置spark可用内存
				.set("spark.executor.memory", "1g")
				// 设置失败尝试次数,建议根据数据量大小来设置，不设置则默认次数为4次
				.set("spark.task.maxFailures", "10");

		return sparkConf;
	}
}
