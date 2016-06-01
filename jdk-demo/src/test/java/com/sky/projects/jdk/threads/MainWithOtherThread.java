package com.sky.projects.jdk.threads;

import com.projects.sky.util.base.Threads;

public class MainWithOtherThread {

	public static class HelloThread implements Runnable {
		private int index = 1;

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("==> HelloThread running: " + index++);
				Threads.sleep(1030);
			}

			System.out.println("==> HelloThread stopped...");
		}
	}

	public static void main(String[] args) {
		int idx = 1;

		for (int i = 0; i < 2; i++) {
			System.out.println("==> Main thread running: " + idx++);
			Threads.sleep(2101);
		}

		HelloThread helloThread = new HelloThread();
		new Thread(helloThread).start();

		for (int i = 0; i < 3; i++) {
			System.out.println("Main thread running " + idx++);
			Threads.sleep(2101);
		}

		System.out.println("==> Main thread stopped...");
	}
}
