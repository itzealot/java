package com.zt.test.thread.test3;

import java.util.ArrayList;
import java.util.List;

public class FileSearchThreadManager implements Runnable {

	private String[] filePath;

	// 线程数量
	private int threadCounts;

	// 总的结果集
	private List<String> allResults = new ArrayList<String>();

	/**
	 * To set the file Path
	 * 
	 * @param filePath
	 */
	public FileSearchThreadManager(String[] filePath) {
		this.filePath = filePath;

		this.threadCounts = filePath.length;
	}

	/**
	 * To add List<String> lists result into allResults
	 * 
	 * @param lists
	 */
	public void countsResult(List<String> lists) {
		allResults.addAll(lists);
	}

	public String[] getFilePath() {
		return filePath;
	}

	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}

	public int getThreadCounts() {
		return threadCounts;
	}

	public void setThreadCounts(int threadCounts) {
		this.threadCounts = threadCounts;
	}

	public void run() {
		// 创建多个线程，执行搜索任务
		for (int i = 0; i < threadCounts; i++) {
			FileSearchThread fileSearchThread = new FileSearchThread(
					filePath[i]);
			// 启动线程
			Thread thread = new Thread(fileSearchThread);
			thread.setName("Thread-t" + i);
			thread.start();
		}
	}

}
