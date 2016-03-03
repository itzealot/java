package com.apusic.distribute.kafka.serializable;

import com.apusic.distribute.kafka.util.SerializerUtil;
import com.apusic.distribute.message.model.MessageEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

/**
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
		else
			return SerializerUtil.serializeToByte(message);
	}

	@Override
	public void close() {

	}
}
