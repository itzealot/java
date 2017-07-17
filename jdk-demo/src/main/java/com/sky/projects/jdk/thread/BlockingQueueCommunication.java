package com.sky.projects.jdk.thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用阻塞队列完成线程间的互斥运行
 * 
 * @author zealot
 */
public class BlockingQueueCommunication {

	public static void main(String[] args) {
		final BufferData data = new BufferData();

		new Thread(() -> {
			while (true) {
				try {
					data.methoA(10);
				} catch (InterruptedException e) {
				}
			}
		}).start();

		new Thread(() -> {
			while (true) {
				try {
					data.methoB(20);
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}
}

/**
 * 使用阻塞队列实现间隔访问输出
 */
class BufferData {
	// 初始化容量为1的阻塞队列
	private BlockingQueue<Integer> queueA = new ArrayBlockingQueue<Integer>(1);

	// 初始化容量为1的阻塞队列
	private BlockingQueue<Integer> queueB = new ArrayBlockingQueue<Integer>(1);

	{ // 对象初始化，匿名构造方法，创建对象将调用
		try {
			queueB.put(10);
		} catch (InterruptedException e) {
		}
	}

	public void methoA(int i) throws InterruptedException {
		queueA.put(10); // 阻塞队列容量为1，向A中放数据，若中有数据则阻塞

		runA(); // TODO run A

		queueB.take(); // B取出数据，即唤醒B
	}

	private void runA() {
		System.out.println("methoA..............................");
		Threads.sleep(500 + new Random().nextInt(500));
	}

	public void methoB(int i) throws InterruptedException {
		queueB.put(10); // 向B中放数据，若queueB中有数据则methoB 阻塞在此处

		runB(); // TODO run B

		queueA.take(); // A取出数据，即唤醒A
	}

	private void runB() {
		System.out.println("methoB..............................");
		Threads.sleep(500 + new Random().nextInt(500));
	}
}