package com.zt.test.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication {

	public static void main(String[] args) {
		final BufferData data = new BufferData();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					data.methoA(10);
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					data.methoB(20);
				}
			}
		}).start();
	}
}

/**
 * 使用阻塞队列实现间隔访问输出
 * 
 * @author zengtao
 */
class BufferData {

	// 初始化容量为1的阻塞队列 queueA
	private BlockingQueue<Integer> queueA = new ArrayBlockingQueue<Integer>(1);

	// 初始化容量为1的阻塞队列 queueB
	private BlockingQueue<Integer> queueB = new ArrayBlockingQueue<Integer>(1);

	/**
	 * To init queueB.<br />
	 * 匿名构造方法，创建对象将调用.<br />
	 */
	{
		try {
			queueB.put(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void methoA(int i) {
		try {
			// 向A中放数据，若queueA中有数据则methoA 阻塞在此处
			queueA.put(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("methoA..............................");

		try {
			// B取出数据，即唤醒B
			queueB.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void methoB(int i) {
		try {
			// 向B中放数据，若queueB中有数据则methoB 阻塞在此处
			queueB.put(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("methoB..............................");

		try {
			// A取出数据，即唤醒A
			queueA.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}