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
	 * @param eventType
	 * @param eventListener
	 */
	<T extends Serializable> void addMessageEventListener(
															String groupId, 
															String eventType,
															MessageEventListener<T> eventListener);

	/**
	 * 监听一组事件
	 * 
	 * @param groupId
	 * @param eventTypes
	 * @param eventListener
	 */
	<T extends Serializable> void addMessageEventListener(
															String groupId, List<String> eventTypes,
															MessageEventListener<T> eventListener);

}
