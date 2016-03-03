package com.apusic.distribute.message.publisher;

import com.apusic.distribute.message.model.MessageEvent;

import java.io.Serializable;

/**
 * 发送事件接口
 * 
 * @author zt
 *
 * @param <T>
 */
public interface MessageEventPublisher<T extends Serializable> {

	void submitMessageEvent(MessageEvent<T> event);

}
