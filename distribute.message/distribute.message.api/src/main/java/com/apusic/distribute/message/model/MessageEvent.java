package com.apusic.distribute.message.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息事件，包含事件类型，消息，日期
 * 
 * @author zt
 *
 * @param <T>
 */
public class MessageEvent<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 6213014733727673739L;

	// 事件类型
	private String eventType;

	// 消息
	private T message;

	// 日期
	private Date time;

	public MessageEvent() {
		time = new Date();
	}

	public MessageEvent(String eventType, T message) {
		this();
		this.eventType = eventType;
		this.message = message;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "MessageEvent{" + "eventType='" + eventType + '\'' + ", message=" + message + ", time=" + time + '}';
	}
}
