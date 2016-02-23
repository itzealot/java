package com.apusic.distribute.kafka.serializable;

import com.apusic.distribute.kafka.util.SerializerUtil;
import com.apusic.distribute.message.model.MessageEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by a on 2016/1/18.
 */
public class MessageEventSerializer implements Serializer<MessageEvent> {

    public MessageEventSerializer() {

    }

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, MessageEvent message) {
        if (message == null)
            return null;
        else
            return SerializerUtil.serializeToByte(message);
    }

    public void close() {

    }
}
