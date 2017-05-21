package com.sky.projects.design.gs.result.impl;

import com.sky.projects.design.common.Threads;
import com.sky.projects.design.gs.result.Data;

/**
 * RealData 的代理对象，封装了获取 RealData 的等待过程，即在没有构建好 RealData 之前一直阻塞
 * 
 * @author zealot
 */
public class FutureData implements Data {

	private RealData data = null;
	private volatile boolean isReady = false;

	/**
	 * 设置真实的数据，如果已经设置则直接返回
	 */
	public synchronized void setRealData(RealData data) {
		if (isReady) {
			return;
		}

		// 装入 RealData
		this.data = data;
		this.isReady = true;

		// 唤醒阻塞方法
		this.notifyAll();
	}

	@Override
	public synchronized String getResult() {
		/*
		 * 使用 while 循环，如果没有准备好则一直阻塞,使用 if 如果有执行 notify or notifyAll 方法则会跳过
		 */
		while (!isReady) {
			// 不使用 synchronized 会出错
			Threads.wait(this);
		}

		return data.getResult();
	}
}
