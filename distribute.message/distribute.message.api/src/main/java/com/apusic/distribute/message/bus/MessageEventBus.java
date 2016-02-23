package com.apusic.distribute.message.bus;

import com.apusic.distribute.message.listener.MessageEventListener;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a on 2016/1/19.
 * �¼���������
 */
public interface MessageEventBus {

    /**
     * ���������¼�
     *
     * @param eventType
     * @param eventListener
     */
    <T extends Serializable> void addMessageEventListener(String groupId, String eventType, MessageEventListener<T> eventListener);

    /**
     * ����һ���¼�
     *
     * @param eventTypes
     * @param eventListener
     */
    <T extends Serializable> void addMessageEventListener(String groupId, List<String> eventTypes, MessageEventListener<T> eventListener);

}
