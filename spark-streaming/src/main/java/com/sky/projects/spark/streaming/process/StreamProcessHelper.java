package com.sky.projects.spark.streaming.process;

import java.util.HashMap;
import java.util.Map;

import kafka.common.TopicAndPartition;

public final class StreamProcessHelper {

	public static Map<TopicAndPartition, Long> topicOffsetToMap(String zkConn, String groupId, String topic,
			Map<Integer, Long> partionAndOffset) {
		long lastConsumedOffset = 1L;
		int partition = 5;
		Map<TopicAndPartition, Long> topicMap = new HashMap<TopicAndPartition, Long>();
		topicMap.put(new TopicAndPartition(topic, partition), lastConsumedOffset);
		return topicMap;
	}

	private StreamProcessHelper() {
	}
}
