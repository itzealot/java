package com.zt.test.thread.test4;

/**
 * 使用实现的同一个Runnable接口的类实现共享数据
 * 
 * @author zengtao
 *
 */
public class MultipleThreadShareData {
	public static void main(String[] args) {

		Thread[] threads = new Thread[2];

		// 使用实现的同一个Runnable接口的类实现共享数据
		threads[0] = new Thread(new ShareData());
		threads[1] = new Thread(new ShareData());

		// To start the threads
		for (int i = 0; i < 2; i++) {
			threads[i].start();
		}
	}
}

/**
 * 
 * @author zengtao
 *
 */
class ShareData implements Runnable {
	private int data = 0;

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			data++;
			show();
		}
	}

	public void show() {
		System.out.println(Thread.currentThread().getName() + " : " + data);
	}
}