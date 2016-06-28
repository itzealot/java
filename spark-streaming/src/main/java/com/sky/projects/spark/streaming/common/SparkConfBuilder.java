package com.sky.projects.spark.streaming.common;

import org.apache.spark.SparkConf;

public class SparkConfBuilder {

	public static SparkConf build(String clusterType, String kafkaReceiverMaxRate, String streamingBlockInterval,
			String kafkaMaxRatePerPartition, String mdssHome) {
		SparkConf conf = new SparkConf();

		conf.setAppName("WL_DataProcess");
		// 设置spark并行执行的job数
		conf.set("spark.streaming.concurrentJobs", "6");
		// conf.set("spark.akka.frameSize", "256");
		// conf.set("spark.akka.threads", "10");
		// conf.set("spark.default.parallelism", "3");
		conf.setMaster(clusterType);

		conf.set("spark.streaming.receiver.maxRate", kafkaReceiverMaxRate);
		conf.set("spark.streaming.blockInterval", streamingBlockInterval);
		conf.set("spark.driver.maxResultSize", "0");
		conf.set("spark.streaming.kafka.maxRatePerPartition", kafkaMaxRatePerPartition);

		// spark1.3.0不需要writeAheadLog
		// conf.set("spark.streaming.receiver.writeAheadLog.enable", "true");
		conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
		conf.setExecutorEnv("MDSS_HOME", mdssHome);
		conf.set("spark.kryo.registrator", "com.surfilter.mass.main.StreamRegistrator");

		return conf;
	}
}
