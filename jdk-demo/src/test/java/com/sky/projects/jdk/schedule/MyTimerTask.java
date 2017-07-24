package com.sky.projects.jdk.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 * 
 * @author zealot
 *
 */
public class MyTimerTask extends TimerTask {
	private Timer timer = new Timer();

	public static void main(String[] args) {
		MyTimerTask t = new MyTimerTask();
		t.start();
	}

	@Override
	public void run() {
		// 以下信息将自动一秒执行一次
		printchar();
	}

	/**
	 * 启动定时器，一秒执行一次
	 */
	public void start() {
		/**
		 * @param task:TimerTask
		 * @param Date:firstTime
		 * @param long:period
		 */
		timer.scheduleAtFixedRate(this, new Date(new Date().getTime() + 5000), 1000);
	}

	/**
	 * 打印信息
	 */
	public void printchar() {
		Date date = new Date();
		System.out.println("定时执行程序:" + new SimpleDateFormat().format(date.getTime()));
	}
}