package com.apusic.distribute.message.publisher;

import com.apusic.distribute.message.model.MessageEvent;

import java.io.Serializable;

/**
 * Created by a on 2016/1/19.
 * 发送事件接口
 */
public interface MessageEventPublisher<T extends Serializable> {

    void submitMessageEvent(MessageEvent<T> event);

}
