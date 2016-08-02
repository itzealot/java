package com.sky.projects.jafka.producer;

import java.util.Properties;

import com.sohu.jafka.producer.Producer;
import com.sohu.jafka.producer.ProducerConfig;
import com.sohu.jafka.producer.StringProducerData;
import com.sohu.jafka.producer.serializer.StringEncoder;

public class SkyProducer {
	public static void main(String[] args) {
		Properties props = new Properties();

		// 指明获取发送地点的地址
		props.setProperty("zk.connect", "localhost:2181");
		props.setProperty("serializer.class", StringEncoder.class.getName());

		Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));

		for (int i = 0; i < 1000; i++) {
			// 构造消息并发送
			producer.send(new StringProducerData("hehe", "hehe-data" + i));
		}
		producer.close();
	}
}
