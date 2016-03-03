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
	 * 消息处理
	 * 
	 * @param eventMessage
	 */
	void handler(MessageEvent<T> eventMessage);

}
