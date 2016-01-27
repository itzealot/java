package com.zt.test.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 * 
 * @author a
 *
 */
public class MyTimerTask extends TimerTask {
	private Timer timer = new Timer();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTimerTask t = new MyTimerTask();
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		/**
		 * 以下信息将自动一秒执行一次
		 */
		System.out.println("备份程序运行……");
		printchar();
	}

	/**
	 * 启动定时器
	 */
	public void start() {
		/**
		 * 一秒执行一次
		 */
		timer.scheduleAtFixedRate(this, new Date(), 1000);
	}

	/**
	 * 打印信息
	 */
	public void printchar() {
		Date date = new Date();
		System.out.println("定时执行程序:" + new SimpleDateFormat().format(date.getTime()));
	}
}