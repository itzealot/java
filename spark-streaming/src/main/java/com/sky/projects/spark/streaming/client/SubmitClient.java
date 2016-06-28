package com.sky.projects.spark.streaming.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.yarn.Client;
import org.apache.spark.deploy.yarn.ClientArguments;

/**
 * 在yarn平台上面，提交spark任务
 * 
 * @author zt
 *
 */
public class SubmitClient {
	
	public static void main(String[] args) throws Exception {
		String streamHome = args[0];
		String streamTypeStr = args[1];
		String jarName = args[2];
		String addJars = args[3].replace(":", ",file://").substring(1);
		String appName = "appName";
		String checkpoint = streamHome + "/" + "checkpoint";
		
		// 注意：值中包含逗号，请用getStrings方法
		String zkQuorum = "";
		String groupId = "";
		String bootstrapServers = "";
		String redisServerInfo = "";
		String idrillerConn = "";
		String mysqlConn = "";
		
		String mdssHome = "";
		String streamClass = "";
		String kafkaMode = "";
		
		String coreSite = "";
		String hdfsSite = "";
		String yarnSite = "";
		
		String driverMemory = null;
		String numExecutors = null;
		String executorMemory = null;
		String executorCores = null;
		
		String driverJavaOptions = null;
		
		String kafkaTopic = null;
		String kafkaMaxRatePerPartition = null;
		String kafkaReceiverMaxRate = null;
		String streamingBlockInterval = null;
		String streamingDuration = null;
		String groupByKeyNum = null;
		
		driverMemory = "";
		numExecutors = "";
		executorMemory = "";
		executorCores = "";
		driverJavaOptions = "";
		
		kafkaTopic = "";
		kafkaMaxRatePerPartition = "";
		kafkaReceiverMaxRate = "";
		streamingBlockInterval = "";
		streamingDuration = "";
		groupByKeyNum = "";

		// org.apache.spark.deploy.yarn.Client object
		String[] arguments = new String[] {
				// the name of your application
				"--name", appName,
				// memory for driver (optional)
				"--driver-memory", driverMemory,
				"--num-executors", numExecutors,
				"--executor-memory", executorMemory,
				"--executor-cores", executorCores,
				
				// path to your application's JAR file
				// required in yarn-cluster mode
				"--jar", jarName,

				// name of your application's main class (required)
				"--class", streamClass,

				// comma separated list of local jars that want
				// SparkContext.addJar to work with
				"--addJars", addJars,

				"--arg", checkpoint, 
				"--arg", zkQuorum, 
				"--arg", groupId, 
				"--arg", redisServerInfo, 
				"--arg", idrillerConn,
				"--arg", mysqlConn, 
				"--arg", kafkaTopic, 
				"--arg", mdssHome, 
				"--arg", streamTypeStr, 
				"--arg", kafkaReceiverMaxRate,
				"--arg", streamingBlockInterval, 
				"--arg", streamingDuration, 
				"--arg", groupByKeyNum,
				"--arg", bootstrapServers,
				"--arg", kafkaMaxRatePerPartition,
				"--arg", kafkaMode,
				"--arg", "yarn-cluster" };
		// create a Hadoop Configuration object
		Configuration config = new Configuration();
		
		config.addResource(new Path(coreSite));
		config.addResource(new Path(hdfsSite));
		config.addResource(new Path(yarnSite));
		
		// validate args
		validate();
		
		System.out.println("yarn.resourcemanager.address:"
				+ config.get("yarn.resourcemanager.address"));

		// identify that you will be using Spark as YARN mode
		System.setProperty("SPARK_YARN_MODE", "true");
		// create an instance of SparkConf object
		SparkConf sparkConf = new SparkConf();
		
		// spark log4j
		String sparkLogFile = "file:///etc/spark/conf/log4j.properties";
		
//		sparkConf.set("spark.yarn.am.extraJavaOptions", " -Xmn1g -XX:PermSize=256m -XX:MaxPermSize=512m -XX:+PrintGC -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -XX:+CMSConcurrentMTEnabled  -XX:ConcGCThreads=8 -XX:+CMSParallelRemarkEnabled");
//		sparkConf.set("spark.executor.extraJavaOptions", " -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/appslog/disk2/dump/file.hprof");
		sparkConf.set("spark.executor.extraJavaOptions", "-Dlog4j.configuration="+ sparkLogFile
				+ " -XX:PermSize=128m -XX:MaxPermSize=256m -XX:+PrintGC -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -XX:+CMSConcurrentMTEnabled  -XX:ConcGCThreads=8 -XX:+CMSParallelRemarkEnabled");
		sparkConf.set("spark.driver.extraJavaOptions", "-Dlog4j.configuration=" + sparkLogFile + " " + driverJavaOptions);
		
		// create ClientArguments, which will be passed to Client
		ClientArguments cArgs = new ClientArguments(arguments, sparkConf);
		// create an instance of yarn Client client
		Client client = new Client(cArgs, config, sparkConf);
		// submit Spark job to YARN
		ApplicationId applicationId = client.submitApplication();
		System.out.println("applicationId:"+applicationId.toString());
//		client.run();
	}

	private static void validate() {
		
	}

}
