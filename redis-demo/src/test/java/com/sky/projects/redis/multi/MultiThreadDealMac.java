package com.sky.projects.redis.multi;

public class MultiThreadDealMac {

	public static void main(String[] args) {
		Thread[] thread = new Thread[10];
		Integer start = 10000;

		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new DealMacThread(start + i * 500));

			thread[i].start();
		}

	}
}
