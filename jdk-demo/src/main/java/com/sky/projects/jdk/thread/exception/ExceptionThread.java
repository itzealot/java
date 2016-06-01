package com.sky.projects.jdk.thread.exception;

import java.util.Random;

import com.projects.sky.util.base.Threads;

public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			Random r = new Random();

			int i = r.nextInt(100);
			System.out.println("Random next value: " + i);

			Threads.sleep(1000);

			if (i > 70) {
				// Simulate an exception was not handled in the thread.
				throw new RuntimeException("Run Random thread have a problem...");
			}
		}
	}

}
