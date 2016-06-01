package com.sky.projects.apache.common.io.monitor;

public class FileListenerTest {

	public static void main(String[] args) {
		FileListener listener = new FileListener("E:/monitor/test.txt");
		listener.setDelay(10000);

		Thread thread = new Thread(listener);
		thread.start();
	}
}
