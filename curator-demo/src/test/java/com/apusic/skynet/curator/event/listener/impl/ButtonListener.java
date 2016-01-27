package com.apusic.skynet.curator.event.listener.impl;

import com.apusic.skynet.curator.event.Button;
import com.apusic.skynet.curator.event.listener.Listener;
import com.apusic.skynet.curator.event.monitor.CallBack;

public class ButtonListener implements Listener {
	@Override
	public void onClick(Button btn, CallBack call) {
		System.out.println(btn + " 被点击了..");

		// 执行回调函数
		call.callBack(btn);
	}

}
