package com.sky.projects.test.demo;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier(int) : 设置parties、count 及 barrierCommand 属性。
 * 
 * CyclicBarrier(int, Runnable) : 当await的数量到达了设定的数量后，首先执行该Runnable对象。
 * 
 * await() : 通知 barrier 已完成线程
 * 
 * @author zt
 *
 */
public class PlayerDemo {
	public static void main(String[] args) throws InterruptedException {
		final Player[] players = new Player[4];

		// 线程池
		ExecutorService exec = Executors.newFixedThreadPool(4);

		CyclicBarrier barrier = new CyclicBarrier(4);

		// 新建队员
		for (int i = 0; i < 4; i++) {
			players[i] = new Player("队员" + (i + 1), barrier, i == 0);
		}

		int i = 0;
		for (; i < 3; i++) {
			players[i].setNext(players[i + 1]);// 设置下一个接力队员
			exec.submit(players[i]);
		}
		exec.submit(players[i]);

		exec.shutdown();
	}
}
