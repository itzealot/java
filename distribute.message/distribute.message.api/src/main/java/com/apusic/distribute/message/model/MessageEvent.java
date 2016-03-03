package com.apusic.distribute.message.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by a on 2016/1/18. 消息事件对象，event为事件类型 message为事件消息
 */
public class MessageEvent<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 6213014733727673739L;
	private String eventType;
	private T message;
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
