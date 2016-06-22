package com.sky.projects.jdk.thread.demo1;

import java.util.ArrayList;
import java.util.List;

public class FileSearchThread implements Runnable {

	private String fileName;

	// 结果
	private List<String> lists;

	public FileSearchThread(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 重写run方法
	 */
	public void run() {
		List<String> result = new ArrayList<String>();

		FileSearchUtil.searchByKeywords(result, "算法", fileName);

		// 执行完成时，将将结果集保存到
		this.lists = result;
	}

	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

}
