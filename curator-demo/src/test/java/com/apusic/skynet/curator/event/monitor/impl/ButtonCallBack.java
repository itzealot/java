package com.apusic.skynet.curator.event.monitor.impl;

import com.apusic.skynet.curator.event.Button;
import com.apusic.skynet.curator.event.monitor.CallBack;

public class ButtonCallBack implements CallBack {

	@Override
	public void callBack(Button btn, Object... objects) {
		System.out.println(btn + " 执行回调..");
	}

}
