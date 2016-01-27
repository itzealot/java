package com.zt.test.thread;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class TestTraditionalTimer {

	public static void test1() {
		/**
		 * 计时器 <br />
		 * 1000 : 10s以后 ;过多少时间开始启动<br />
		 */
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("bombing");
			}
		}, 10000);

		while (true) {
			try {

				// To get the system's second
				Calendar calendar = GregorianCalendar.getInstance();
				System.out.println(calendar.get(Calendar.SECOND));

				// 休息一秒
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test2() {
		/**
		 * 计时器 <br />
		 * 1000 : 10s以后 ;过多少时间开始启动<br />
		 * 3000 : 每3秒；每隔多少秒继续启动 <br />
		 */
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
			try {

				// To get the system's second
				Calendar calendar = GregorianCalendar.getInstance();
				System.out.println(calendar.get(Calendar.SECOND));

				// 休息一秒
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

		/**
		 * 间隔2秒启动定时器
		 */
		new Timer().schedule(new MyTimeTask(), 2000);
	}
}
