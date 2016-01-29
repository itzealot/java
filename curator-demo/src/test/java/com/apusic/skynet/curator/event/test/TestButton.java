package com.apusic.skynet.curator.event.test;

import org.junit.Test;

import com.apusic.skynet.curator.event.Button;
import com.apusic.skynet.curator.event.listener.impl.ButtonListener;
import com.apusic.skynet.curator.event.monitor.impl.ButtonCallBack;

public class TestButton {

	@Test
	public void test() {
		Button btn = new Button("btn1", new ButtonListener(), new ButtonCallBack());

		// 模拟按钮被点击
		btn.click();
	}
}
