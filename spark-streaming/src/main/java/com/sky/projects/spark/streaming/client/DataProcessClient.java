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
import com.sky.projects.spark.streaming.common.SparkConfBuilder;
import com.sky.projects.spark.streaming.process.DemoStreamingProcess;

import redis.clients.jedis.Jedis;

public class DataProcessClient {
	private static Logger LOG = Logger.getLogger(DataProcessClient.class);

	public static void main(String[] args) {
		final String checkpointDirectory = args[0];
		final String zkQuorum = args[1];
		final String groupId = args[2];
		final String redisServerInfo = args[3];
		final String idrillerConn = args[4];
		final String mysqlConn = args[5];
		final String topic = args[6];
		final String mdssHome = args[7];
		final String streamTypeStr = args[8];
		final String kafkaReceiverMaxRate = args[9];
		final String streamingBlockInterval = args[10];
		final String streamingDuration = args[11];
		final String groupByKeyNum = args[12];
		final String bootstrapServers = args[13];
		final String kafkaMaxRatePerPartition = args[14];
		final String kafkaMode = args[15];
		final String clusterType = args[16];

		final boolean[] filterArr = null;
		final String[] systemParamArr = null;

		JavaStreamingContextFactory factory = new JavaStreamingContextFactory() {
			@Override
			public JavaStreamingContext create() {
				return createContext(checkpointDirectory, zkQuorum, groupId, redisServerInfo, idrillerConn, mysqlConn,
						topic, mdssHome, streamTypeStr, kafkaReceiverMaxRate, streamingBlockInterval, streamingDuration,
						groupByKeyNum, bootstrapServers, kafkaMaxRatePerPartition, kafkaMode, clusterType, filterArr,
						systemParamArr);
			}
		};

		JavaStreamingContext jssc = JavaStreamingContext.getOrCreate(checkpointDirectory, factory);

		jssc.start();
		jssc.awaitTermination();
	}

	private static JavaStreamingContext createContext(String checkpointDirectory, String zkQuorum, String groupId,
			String redisServerInfo, String idrillerConn, String mysqlConn, String topic, String mdssHome,
			String streamTypeStr, String kafkaReceiverMaxRate, String streamingBlockInterval, String streamingDuration,
			String groupByKeyNum, String bootstrapServers, String kafkaMaxRatePerPartition, String kafkaMode,
			String clusterType, boolean[] filterArr, String[] systemParamArr) {

		SparkConf conf = SparkConfBuilder.build(clusterType, kafkaReceiverMaxRate, streamingBlockInterval,
				kafkaMaxRatePerPartition, mdssHome);

		JavaSparkContext jsc = new JavaSparkContext(conf);
		JavaStreamingContext jssc = new JavaStreamingContext(jsc, Seconds.apply(Long.parseLong(streamingDuration)));
		jssc.checkpoint(checkpointDirectory);

		// 广播
		Map<String, Broadcast<String>> broadcastMap = new HashMap<String, Broadcast<String>>();

		broadcastMap.put("bRedisInfo", jsc.broadcast(redisServerInfo));
		broadcastMap.put("bIdrillerConn", jsc.broadcast(idrillerConn));
		broadcastMap.put("bMysqlConn", jsc.broadcast(mysqlConn));
		broadcastMap.put("bZkConn", jsc.broadcast(zkQuorum));
		broadcastMap.put("bGroupId", jsc.broadcast(groupId));

		Map<String, Broadcast<Map<String, String>>> broadcastRedisMap = new HashMap<String, Broadcast<Map<String, String>>>();

		Map<String, String> kafkaParams = KafkaUtil.getKafkaConf(bootstrapServers, zkQuorum, groupId);

		String certProtocolRedisKey = "";
		String macHasCompanyRedisKey = "";
		boolean checkFlag = false;
		checkFlag = addRedisBroadcast(jsc, broadcastRedisMap, "", null, certProtocolRedisKey);
		if (!checkFlag) {
			return null;
		}

		checkFlag = addRedisBroadcast(jsc, broadcastRedisMap, "", null, macHasCompanyRedisKey);
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

		new DemoStreamingProcess(conf, broadcastRedisMap, broadcastMap, Long.parseLong(streamingDuration),
				Integer.parseInt(groupByKeyNum), systemParamArr).deal(jssc, kafkaParams, topicMap, partionAndOffset);

		return jssc;
	}

	private static boolean addRedisBroadcast(JavaSparkContext jsc,
			Map<String, Broadcast<Map<String, String>>> broadcastRedisMap, String broadcastKey, Jedis redisDao,
			String redisKey) {
		boolean returnFlag = false;
		Map<String, String> valueMap = redisDao.hgetAll(redisKey);
		if (valueMap == null || valueMap.size() < 1) {
			LOG.error("redis key(" + redisKey + ") fetch error! please check redis");
		} else {
			final Broadcast<Map<String, String>> bValueMap = jsc.broadcast(valueMap);
			broadcastRedisMap.put(broadcastKey, bValueMap);
			returnFlag = true;
		}
		return returnFlag;
	}

	/**
	 * 获取kafka的消费偏移(OFFSET)
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
				partionAndOffset = KafkaUtil.getLastestOffset(topic, bootstrapServers);
			} else if (kafkaMode.equalsIgnoreCase("early")) {
				partionAndOffset = KafkaUtil.getEarliestOffset(topic, bootstrapServers);
			} else {
				throw new Exception("kafkaMode config error, must be (last or early)");
			}
		} catch (Exception e) {
			LOG.error("Fetch earliestOffset error", e);
			throw new Exception("Fetch earliestOffset error", e);
		}

		return partionAndOffset;
	}

}
