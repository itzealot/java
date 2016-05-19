package com.sky.projects.jdk.thread;

public class RunnerTest {

	public static void main(String[] args) {
		Runner runner = new Runner();

		Thread thread = new Thread(runner);
		thread.start();

		Thread thread2 = new Thread(new RunnerClose(runner, 5));
		thread2.start();
	}
}
