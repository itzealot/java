package com.sky.projects.test.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 共享 CountDownLatch 的工作
 * 
 * @author zt
 *
 */
public class Worker implements Runnable {
	private String workerName;
	private int workTime;

	// 共享 CountDownLatch 数据
	private CountDownLatch latch;

	public Worker() {
	}

	public Worker(String workerName, int workTime, CountDownLatch latch) {
		this.workerName = workerName;
		this.workTime = workTime;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("Worker " + workerName + " do work begin at " + sdf.format(new Date()));

			doWork(); // do work

			System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
		} finally {
			// 必须执行
			latch.countDown(); // 工人完成工作，计数器减一
		}
	}

	private void doWork() {
		try {
			Thread.sleep(workTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

}