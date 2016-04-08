package com.apusic.distribute.message.listener;

import com.apusic.distribute.message.model.MessageEvent;

import java.io.Serializable;

/**
 * 事件接收接口
 * 
 * @author zt
 *
 * @param <T>
 */
public interface MessageEventListener<T extends Serializable> {

	/**
	 * 接收消息
	 * 
	 * @param eventMessage
	 *            消息
	 */
	void handler(MessageEvent<T> eventMessage);

}
