package com.apusic.distribute.message.bus;

import com.apusic.distribute.message.listener.MessageEventListener;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a on 2016/1/19.
 * 事件处理中心
 */
public interface MessageEventBus {

    /**
     * 监听单个事件
     *
     * @param eventType
     * @param eventListener
     */
    <T extends Serializable> void addMessageEventListener(String groupId, String eventType, MessageEventListener<T> eventListener);

    /**
     * 监听一组事件
     *
     * @param eventTypes
     * @param eventListener
     */
    <T extends Serializable> void addMessageEventListener(String groupId, List<String> eventTypes, MessageEventListener<T> eventListener);

}
