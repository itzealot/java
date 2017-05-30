package com.sky.projects.jdk.syn;

public class ThreadTest {

	public static void main(String[] args) {
		new Thread(new SynThread1(new SynResource())).start();
		new Thread(new SynThread2(new SynResource())).start();

		new Thread(new SynStaticThread1()).start();
		new Thread(new SynStaticThread2()).start();
	}

}
