package com.sky.projects.jdk.thread.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

import com.projects.sky.util.base.Threads;

/**
 * 生产者与消费者
 * 
 * @author zengtao
 *
 */
public class TestProducterAndConsumer {
	public static void main(String[] args) {
		// testOrigin();
		testChange();
	}

	public static void testChange() {
		// 同步队列，也是阻塞队列；可查看帮助文档,放了之后才能取
		final SynchronousQueue<String> queue = new SynchronousQueue<String>();

		// 使用信号量值为1进行资源的互斥访问
		final Semaphore semaphore = new Semaphore(1);

		// 创建10个消费者线程
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						// 锁住同步资源(此处只有一个资源)
						semaphore.acquire();

						// 从队列中取数据
						String input = queue.take();

						// 处理数据并返回结果
						String output = TestDao.doSome(input);

						// 输出
						System.out.println(Thread.currentThread().getName() + " : " + output);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						// 释放同步资源，必须放在 finally 中执行
						semaphore.release();
					}
				}
			}).start();
		}

		// 产生数据并交给TestDao 处理，类似于生产者不断产生数据，消费者不断消费数据.
		for (int i = 0; i < 10; i++) { // 这行不能改动
			String input = i + ""; // 这行不能改动

			try {
				// 将产生的数据放入到queue中
				queue.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 原始程序如下,要求将程序改成：<br />
	 * 1. 有10个线程来消费生产者产生的数据，消费者都调用TestDao 方法处理;<br />
	 * 2. 故每个消费者都需要1秒才能处理完，程序应保证这些消费者依次有序的消费数据;<br />
	 * 3. 只有上一个消费者消费完，下一个消费者才能消费数据，下一个消费者谁都可以.
	 */
	public static void testOrigin() {
		System.out.println("begin : " + (System.currentTimeMillis() / 1000));

		// 产生数据并交给TestDao出口，类似于生产者不断产生数据，消费者不断消费数据.
		for (int i = 0; i < 10; i++) { // 这行不能改动
			String input = i + ""; // 这行不能改动

			// 处理数据并返回结果
			String output = TestDao.doSome(input);

			System.out.println(Thread.currentThread().getName() + " : " + output);
		}
	}
}

/**
 * 不能改动此TestDao类.
 */
class TestDao {
	/**
	 * 处理数据
	 * 
	 * @param input
	 * @return
	 */
	public static String doSome(String input) {
		// 休息一秒，模拟处理数据
		Threads.sleep(1000);

		return input + " : " + (System.currentTimeMillis() / 1000);
	}
}