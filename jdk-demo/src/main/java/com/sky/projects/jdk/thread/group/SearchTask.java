package com.sky.projects.jdk.thread.group;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.sky.projects.jdk.thread.Threads;

public class SearchTask implements Runnable {

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.printf("Thread %s start.\n", name);

		try {
			doTask();
		} catch (InterruptedException e) {
			System.out.printf("Thread %s interrupted.\n", name);
			return;
		}

		System.out.printf("Thread %s finish.\n", name);
	}

	private void doTask() throws InterruptedException {
		TimeUnit.SECONDS.sleep(new Random().nextInt(100));
	}

	public static void main(String[] args) {
		ThreadGroup group = new ThreadGroup("Searcher");

		// 创建5个线程，并入group里面进行管理
		for (int i = 0; i < 5; i++) {
			new Thread(group, new SearchTask()).start();
			Threads.sleep(2);
		}

		// 查看group里面的所有信息
		System.out.printf("group %s active counts: %d\n", group, group.activeCount());

		System.out.printf("Information about the Thread Group\n");
		group.list();

		// 复制group里面的thread信息
		Thread[] threads = new Thread[group.activeCount()];
		group.enumerate(threads);
		for (int i = 0; i < group.activeCount(); i++) {
			System.out.printf("Thread %s state: %s\n", threads[i].getName(), threads[i].getState());
		}

		// 等待完成
		waitFinish(group);

		// 将group里面的所有线程都给interrupt
		group.interrupt();
	}

	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
