package com.sky.projects.spark.streaming.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.streaming.Seconds;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.api.java.JavaStreamingContextFactory;

import com.sky.projects.spark.streaming.common.KafkaUtil;
import com.sky.projects.spark.streaming.process.DemoStreamingProcess;

import redis.clients.jedis.Jedis;

public class StreamingProcessClient {
	private static Logger LOG = Logger.getLogger(StreamingProcessClient.class);

	public static void main(String[] args) {
		final String checkpointDirectory = args[0];
		final String zkConnect = args[1];
		final String groupId = args[2];
		final String redisServerInfo = args[3];
		final String impalaConn = args[4];
		final String mysqlConn = args[5];
		final String topic = args[6];
		final String home = args[7];
		final String streamTypeStr = args[8];
		final String kafkaReceiverMaxRate = args[9];
		final String streamingBlockInterval = args[10];
		final String streamingDuration = args[11];
		final String groupByKeyNum = args[12];
		final String bootstrapServers = args[13];
		final String kafkaMaxRatePerPartition = args[14];
		final String kafkaMode = args[15];
		final String clusterType = args[16];

		JavaStreamingContextFactory factory = new JavaStreamingContextFactory() {
			@Override
			public JavaStreamingContext create() {
				return createContext(checkpointDirectory, zkConnect, groupId, redisServerInfo, impalaConn, mysqlConn,
						topic, home, streamTypeStr, kafkaReceiverMaxRate, streamingBlockInterval, streamingDuration,
						groupByKeyNum, bootstrapServers, kafkaMaxRatePerPartition, kafkaMode, clusterType, null, null);
			}
		};
		JavaStreamingContext context = JavaStreamingContext.getOrCreate(checkpointDirectory, factory);

		context.start();
		context.awaitTermination();
	}

	private static JavaStreamingContext createContext(String checkpointDirectory, String zkQuorum, String groupId,
			String redisServerInfo, String impalaConn, String mysqlConn, String topic, String home,
			String streamTypeStr, String kafkaReceiverMaxRate, String streamingBlockInterval, String streamingDuration,
			String groupByKeyNum, String bootstrapServers, String kafkaMaxRatePerPartition, String kafkaMode,
			String clusterType, boolean[] filterArr, String[] systemParamArr) {

		SparkConf conf = sparkConf(clusterType, kafkaReceiverMaxRate, streamingBlockInterval, kafkaMaxRatePerPartition,
				home);

		JavaSparkContext sparkContext = new JavaSparkContext(conf);
		JavaStreamingContext context = new JavaStreamingContext(sparkContext,
				Seconds.apply(Long.parseLong(streamingDuration)));
		context.checkpoint(checkpointDirectory);

		// 广播
		Map<String, Broadcast<String>> broadcastMap = new HashMap<String, Broadcast<String>>();
		broadcastMap.put("", sparkContext.broadcast(redisServerInfo));
		broadcastMap.put("", sparkContext.broadcast(impalaConn));
		broadcastMap.put("", sparkContext.broadcast(mysqlConn));
		broadcastMap.put("", sparkContext.broadcast(zkQuorum));
		broadcastMap.put("", sparkContext.broadcast(groupId));

		Map<String, Broadcast<Map<String, String>>> broadcastRedisMap = new HashMap<String, Broadcast<Map<String, String>>>();

		Map<String, String> kafkaParams = kafkaProps(bootstrapServers, groupId, zkQuorum);

		String certProtocolRedisKey = "";
		String macHasCompanyRedisKey = "";
		boolean checkFlag = false;
		checkFlag = addRedisBroadcast(sparkContext, broadcastRedisMap, "", null, certProtocolRedisKey);
		if (!checkFlag) {
			return null;
		}

		checkFlag = addRedisBroadcast(sparkContext, broadcastRedisMap, "", null, macHasCompanyRedisKey);
		if (!checkFlag) {
			return null;
		}

		Map<String, Integer> topicMap = new HashMap<String, Integer>();
		topicMap.put(topic, 1);

		Map<Integer, Long> partionAndOffset = null;
		try {
			partionAndOffset = getOffset(kafkaMode, topic, bootstrapServers);
		} catch (Exception e) {
			LOG.error("Fetch kafka offset error", e);
		}
		if (partionAndOffset == null || partionAndOffset.isEmpty()) {
			return null;
		}

		// TODO 新建流式处理类，并执行入口方法
		new DemoStreamingProcess(conf, broadcastRedisMap, broadcastMap, Long.parseLong(streamingDuration),
				Integer.parseInt(groupByKeyNum), systemParamArr).deal(context, kafkaParams, topicMap, partionAndOffset);

		return context;
	}

	private static SparkConf sparkConf(String clusterType, String kafkaReceiverMaxRate, String streamingBlockInterval,
			String kafkaMaxRatePerPartition, String home) {
		SparkConf conf = new SparkConf();

		// AppName
		conf.setAppName("DataProcess");

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
		conf.setExecutorEnv("MDSS_HOME", home);

		// 注册主类
		conf.set("spark.kryo.registrator", "com.sky.projects.spark.streaming.register.StreamRegistrator");

		return conf;
	}

	private static Map<String, String> kafkaProps(String bootstrapServers, String groupId, String zkConnect) {
		Map<String, String> kafkaParams = new HashMap<String, String>();

		// spark1.3.0必须配置bootstrap.servers or metadata.broker.list
		kafkaParams.put("bootstrap.servers", bootstrapServers);
		kafkaParams.put("zookeeper.connect", zkConnect);
		kafkaParams.put("zookeeper.connection.timeout.ms", "10000");
		kafkaParams.put("zookeeper.session.timeout.ms", "6000");
		kafkaParams.put("zookeeper.sync.time.ms", "2000");
		kafkaParams.put("group.id", groupId);
		// kafkaParams.put("consumer.id", "SimpleSparkConsumer");
		kafkaParams.put("auto.offset.reset", "smallest");
		kafkaParams.put("auto.commit.interval.ms", "1000");
		kafkaParams.put("fetch.message.max.bytes", "41943040");
		kafkaParams.put("replica.fetch.max.bytes", "41943040");
		// kafkaParams.put("num.consumer.fetchers", "4");

		return kafkaParams;
	}

	private static boolean addRedisBroadcast(JavaSparkContext context,
			Map<String, Broadcast<Map<String, String>>> broadcastRedisMap, String broadcastKey, Jedis jedis,
			String redisKey) {
		Map<String, String> valueMap = jedis.hgetAll(redisKey);
		if (valueMap == null || valueMap.size() < 1) {
			LOG.error("redis key(" + redisKey + ") fetch error! please check redis");
			return false;
		}
		broadcastRedisMap.put(broadcastKey, context.broadcast(valueMap));
		return true;
	}

	/**
	 * 获取kafka的消费偏移 OFFSET
	 * 
	 * @param kafkaMode
	 * @param topic
	 * @param bootstrapServers
	 * @return
	 * @throws Exception
	 */
	private static Map<Integer, Long> getOffset(String kafkaMode, String topic, String bootstrapServers)
			throws Exception {
		Map<Integer, Long> partionAndOffset = null;

		try {
			if (kafkaMode.equalsIgnoreCase("last")) {
				// TODO To get the recent offset from kafka
				partionAndOffset = KafkaUtil.getLastestOffset(topic, bootstrapServers);
			} else if (kafkaMode.equalsIgnoreCase("early")) {
				// TODO To get the earliest offset from kafka by topic
				partionAndOffset = KafkaUtil.getEarliestOffset(topic, bootstrapServers);
			} else {
				throw new Exception("kafkaMode config error, must be (last or early)");
			}
		} catch (Exception e) {
			// TODO
			LOG.error("Fetch earliestOffset error", e);
			throw new Exception("Fetch earliestOffset error", e);
		}

		return partionAndOffset;
	}

}
