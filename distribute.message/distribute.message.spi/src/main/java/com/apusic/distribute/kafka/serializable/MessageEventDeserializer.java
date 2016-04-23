package com.apusic.distribute.kafka.serializable;

import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.apusic.distribute.message.model.MessageEvent;
import com.projects.sky.util.serialize.Serializables;

/**
 * MessageEvent 反序列化
 * 
 * @author zt
 *
 * @param <T>
 */
public class MessageEventDeserializer<T extends Serializable> implements Deserializer<MessageEvent<T>> {

	public MessageEventDeserializer() {
	}

	@Override
	public void configure(Map<String, ?> map, boolean b) {

	}

	@Override
	public MessageEvent<T> deserialize(String s, byte[] bytes) {
		if (bytes == null)
			return null;
		return Serializables.read(bytes);
	}

	@Override
	public void close() {

	}
}
