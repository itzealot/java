package com.sky.projects.spark.streaming.register;

import org.apache.spark.serializer.KryoRegistrator;

import com.esotericsoftware.kryo.Kryo;
import com.sky.projects.spark.streaming.process.DemoStreamingProcess;

/**
 * 流式处理的注册类
 * 
 * @author zt
 *
 */
public class StreamRegistrator implements KryoRegistrator {
	@Override
	public void registerClasses(Kryo kryo) {
		kryo.register(DemoStreamingProcess.class);
	}
}
