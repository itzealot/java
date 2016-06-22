package com.sky.projects.jdk.thread;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import com.projects.sky.util.base.Threads;

public class TimerTest {

	public static void test1() {
		// 计时器过多少时间(10000 ms)开始启动
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("bombing");
			}
		}, 10000);

		while (true) {
			// 获取系统秒时间
			Calendar calendar = GregorianCalendar.getInstance();
			System.out.println(calendar.get(Calendar.SECOND));

			// 休息一秒
			Threads.sleep(1000);
		}
	}

	public static void test2() {
		// 计时器过多少时间开始启动(10000 ms), 每隔多少秒继续启动(3000 ms)
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("bombing");
			}
		}, 10000, 3000);
	}

	/**
	 * 每隔两秒爆炸，即输出 booming
	 */
	public static void test3() {
		// 2秒后调度计算器
		new Timer().schedule(new MyTimeTask(), 2000);

		while (true) {
			// 获取系统秒时间
			Calendar calendar = GregorianCalendar.getInstance();
			System.out.println(calendar.get(Calendar.SECOND));

			// 休息一秒
			Threads.sleep(1000);
		}
	}

	public static void main(String[] args) {
		test3();
	}
}

/**
 * 自定义计算器
 * 
 * @author zengtao
 *
 */
class MyTimeTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("bombing");

		// 间隔2秒启动定时器
		new Timer().schedule(new MyTimeTask(), 2000);
	}
}
