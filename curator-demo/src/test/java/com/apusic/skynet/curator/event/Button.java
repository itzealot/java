package com.apusic.skynet.curator.event;

import com.apusic.skynet.curator.event.listener.Listener;
import com.apusic.skynet.curator.event.monitor.CallBack;

public class Button {

	private String name;

	private Listener listener;

	private CallBack callBack;

	public Button(String name, Listener listener, CallBack callBack) {
		super();
		this.name = name;
		this.listener = listener;
		this.callBack = callBack;
	}

	// 模拟点击按钮
	public void click() {
		// 触发事件
		listener.onClick(this, callBack);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Button [name=" + name + "]";
	}

}
