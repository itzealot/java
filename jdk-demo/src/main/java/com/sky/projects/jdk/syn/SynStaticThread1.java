package com.sky.projects.jdk.syn;

/**
 * 1. synchronized 关键字作用在静态方法上锁为类对应的 Class 对象
 * 
 * 2. 一个类中的多个静态同步方法被多线程调用，一次只能调用执行该类中的一个方法
 */
public class SynStaticThread1 implements Runnable {
	@Override
	public void run() {
		while (true) {
			SynStaticResource.add1();
		}
	}
}