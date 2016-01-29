package com.sky.projects.test.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 
 * 主要方法:
 * 
 * public CountDownLatch(int count);
 * 
 * public void countDown();
 * 
 * public void await() throws InterruptedException
 * 
 * 
 * 构造方法参数指定了计数的次数
 * 
 * countDown 方法，当前线程调用此方法，则计数减一
 * 
 * awaint 方法，调用此方法会一直阻塞当前线程，直到计时器的值为 0
 * 
 * @author zt
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		// 两个工人的协作
		CountDownLatch latch = new CountDownLatch(2);

		Worker worker1 = new Worker("zhang san", 5000, latch);
		Worker worker2 = new Worker("li si", 8000, latch);

		// 两个线程
		new Thread(worker1).start();
		new Thread(worker2).start();

		// 等待所有工人完成工作
		latch.await();

		System.out.println("all work done at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

}