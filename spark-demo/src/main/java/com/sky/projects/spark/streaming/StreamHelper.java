package com.sky.projects.spark.streaming;

import java.util.Map;

import org.apache.spark.streaming.api.java.JavaDStream;

import kafka.common.TopicAndPartition;

public class StreamHelper {

	public static Map<TopicAndPartition, Long> topicOffsetToMap(String zkConn, String groupId, String topic,
			Map<Integer, Long> partionAndOffset) {
		return null;
	}

	public static void updateOffsetToZk(JavaDStream<String> sourceStream, String sTREAM_TYPE, String zkConn,
			String groupId) {
	}

}
