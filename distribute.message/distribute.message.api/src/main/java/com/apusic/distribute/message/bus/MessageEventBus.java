package com.apusic.distribute.message.bus;

import com.apusic.distribute.message.listener.MessageEventListener;

import java.io.Serializable;
import java.util.List;

/**
 * 事件处理中心接口
 * 
 * @author zt
 *
 */
public interface MessageEventBus {

	/**
	 * 监听单个事件
	 * 
	 * @param groupId
	 *            kafka 消费组 group
	 * @param eventType
	 *            事件类型
	 * @param eventListener
	 *            事件接收接口
	 */
	<T extends Serializable> void addMessageEventListener(
			String groupId,
			String eventType,
			MessageEventListener<T> eventListener);

	/**
	 * 监听一组事件
	 * 
	 * @param groupId
	 *            kafka 消费组 group
	 * @param eventTypes
	 *            事件类型
	 * @param eventListener
	 *            事件接收接口
	 */
	<T extends Serializable> void addMessageEventListener(
			String groupId,
			List<String> eventTypes,
			MessageEventListener<T> eventListener);

}
