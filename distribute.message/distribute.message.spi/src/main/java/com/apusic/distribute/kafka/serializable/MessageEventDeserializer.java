package com.apusic.distribute.kafka.serializable;

import com.apusic.distribute.kafka.util.SerializerUtil;
import com.apusic.distribute.message.model.MessageEvent;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * Created by a on 2016/1/18.
 */
public class MessageEventDeserializer implements Deserializer<MessageEvent> {

    public MessageEventDeserializer() {

    }

    public void configure(Map<String, ?> map, boolean b) {

    }

    public MessageEvent deserialize(String s, byte[] bytes) {
        if (bytes == null)
            return null;
        else
            return SerializerUtil.deserializeFromByte(bytes);
    }

    public void close() {

    }
}
