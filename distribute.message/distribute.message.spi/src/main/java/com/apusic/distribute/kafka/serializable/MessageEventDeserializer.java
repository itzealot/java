package com.apusic.distribute.kafka.serializable;

import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.apusic.distribute.kafka.util.SerializerUtil;
import com.apusic.distribute.message.model.MessageEvent;

/**
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
		else
			return SerializerUtil.deserializeFromByte(bytes);
	}

	@Override
	public void close() {

	}
}
