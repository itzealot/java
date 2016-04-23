package com.apusic.distribute.kafka.serializable;

import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.apusic.distribute.message.model.MessageEvent;
import com.projects.sky.util.serialize.Serializables;

/**
 * MessageEvent 序列化
 * 
 * @author zt
 *
 * @param <T>
 */
public class MessageEventSerializer<T extends Serializable> implements Serializer<MessageEvent<T>> {

	public MessageEventSerializer() {
	}

	@Override
	public void configure(Map<String, ?> map, boolean b) {

	}

	@Override
	public byte[] serialize(String s, MessageEvent<T> message) {
		if (message == null)
			return null;
		return Serializables.serialize(message);
	}

	@Override
	public void close() {

	}
}
