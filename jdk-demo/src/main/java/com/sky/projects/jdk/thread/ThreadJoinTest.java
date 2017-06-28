package com.sky.projects.jdk.thread;

import java.util.Random;

/**
 * join() : 无参数的join()等价于join(0),作用是一直等待该线程死亡,join的线程不死亡,程序就会阻塞在那里
 * 
 * join(long millis, int nanos) : 最多等待该线程死亡millis毫秒
 * 
 * join(long millis, int nanos) : 最多等待该线程死亡millis毫秒加nanos纳秒
 * 
 * @since 1.8
 * @author zealot
 *
 */
public class ThreadJoinTest {

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[5];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " running..............");
				Threads.sleep(1000 + new Random().nextInt(1000));
				System.out.println(Thread.currentThread().getName() + " finish..............");
			}, "Threads-" + i);
		}

		// 保证 Threads 有序执行
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
			threads[i].join(); // 若 threads[i] 未执行完成，threads[i + 1] 不会执行
		}
	}
}
