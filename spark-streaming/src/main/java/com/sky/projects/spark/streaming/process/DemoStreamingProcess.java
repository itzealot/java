package com.sky.projects.spark.streaming.process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.sky.projects.spark.streaming.common.SysConstants;

import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import scala.Tuple2;

@SuppressWarnings("serial")
public class DemoStreamingProcess extends KryoSerializer {

	private String zkConn;
	private String groupId;
	private String topic;
	private Map<Integer, Long> partionAndOffset;

	public DemoStreamingProcess(SparkConf conf, Map<String, Broadcast<Map<String, String>>> broadcastRedisMap,
			Map<String, Broadcast<String>> broadcastMap, long parseLong, int parseInt, String[] systemParamArr) {
		super(conf);
	}

	public void deal(JavaStreamingContext jssc, Map<String, String> kafkaParams, Map<String, Integer> topicMap,
			Map<Integer, Long> partionAndOffset) {
		this.partionAndOffset = partionAndOffset;

		kafkaLoad(jssc, kafkaParams, topicMap);
	}

	public JavaDStream<String[]> kafkaLoad(JavaStreamingContext context, Map<String, String> kafkaParams) {
		JavaDStream<String> sourceStream = KafkaUtils.createDirectStream(context, String.class, String.class,
				StringDecoder.class, StringDecoder.class, String.class, kafkaParams,
				StreamProcessHelper.topicOffsetToMap(zkConn, groupId, topic, partionAndOffset),
				new Function<MessageAndMetadata<String, String>, String>() {
					@Override
					public String call(MessageAndMetadata<String, String> source) throws Exception {
						return source.message();
					}
				});

		JavaDStream<String[]> splitStream = sourceStream.flatMap(new FlatMapFunction<String, String[]>() {
			public Iterable<String[]> call(String json) {
				// To get the json array
				String[] messages = json.split(SysConstants.OBJECT_SPLITER);
				int len = messages.length;
				List<String[]> lines = new ArrayList<String[]>(len);

				// To return the fields into String array
				for (int i = 0; i < len; i++) {
					lines.add(messages[i].split(SysConstants.FILED_SPLITER));
				}

				return lines;
			}
		});

		return splitStream.filter(new Function<String[], Boolean>() {
			@Override
			public Boolean call(String[] currentLine) throws Exception {
				// TODO filter the data
				return true;
			}
		});
	}

	public JavaDStream<String[]> kafkaLoad(JavaStreamingContext jssc, Map<String, String> kafkaParams,
			Map<String, Integer> topicMap) {
		// 1. Kafka load and create DStream
		JavaPairDStream<String, String> sourceStream = KafkaUtils.createDirectStream(jssc, String.class, String.class,
				StringDecoder.class, StringDecoder.class, kafkaParams, topicMap.keySet());

		// TODO 2. execute the operation base on DStream

		// 2.1 flatMap as the Object's filed
		JavaDStream<String[]> splitStream = sourceStream
				.flatMap(new FlatMapFunction<Tuple2<String, String>, String[]>() {
					public Iterable<String[]> call(Tuple2<String, String> tuple) {
						// To get the Object array by object spliter
						String[] messages = tuple._2.split(SysConstants.OBJECT_SPLITER);
						int len = messages.length;
						List<String[]> lines = new ArrayList<String[]>(len);

						// To return the fields into String array
						for (int i = 0; i < len; i++) {
							lines.add(messages[i].split(SysConstants.FILED_SPLITER));
						}

						return lines;
					}
				});

		// do filter
		return splitStream.filter(new Function<String[], Boolean>() {
			@Override
			public Boolean call(String[] currentLine) throws Exception {
				return true;
			}
		});
	}

	/**
	 * 执行 reduceByKey 操作，多个相同的 key 保留一个
	 * 
	 * @param source
	 * @return
	 */
	public JavaDStream<String[]> reduce(JavaDStream<String[]> source) {
		JavaPairDStream<String, String[]> pairStream = source.mapToPair(new PairFunction<String[], String, String[]>() {
			@Override
			public Tuple2<String, String[]> call(String[] str) {
				// TODO 构造去重键用于 reduce 操作
				String key = str[0] + "|" + str[1];
				return new Tuple2<String, String[]>(key, str);
			}
		});

		// 按 key 聚合
		JavaPairDStream<String, String[]> reduceStream = pairStream
				.reduceByKey(new Function2<String[], String[], String[]>() {
					@Override
					public String[] call(String[] o1, String[] o2) {
						// TODO select the one to return above two objects

						// return the max startTime and times add
						if (Long.parseLong(o1[0]) > Long.parseLong(o2[0])) {
							o2[1] = String.valueOf(Long.parseLong(o1[1]) + Long.parseLong(o2[1]));
							return o2;
						} else {
							o1[1] = String.valueOf(Long.parseLong(o1[1]) + Long.parseLong(o2[1]));
							return o1;
						}
					}
				}, 5);

		// mapPartitions 操作，将聚合后的结果(Pair) 使用 flatMap 函数返回单记录信息
		return reduceStream.mapPartitions(new FlatMapFunction<Iterator<Tuple2<String, String[]>>, String[]>() {
			@Override
			public Iterable<String[]> call(Iterator<Tuple2<String, String[]>> messagePairs) throws Exception {
				List<String[]> messages = new ArrayList<String[]>();

				while (messagePairs.hasNext()) {
					String[] message = messagePairs.next()._2;

					// TODO deal the reduce message and do service operation

					messages.add(message);
				}

				return messages;
			}
		});
	}
}
