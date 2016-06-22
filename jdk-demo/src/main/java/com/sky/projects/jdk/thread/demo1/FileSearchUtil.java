package com.sky.projects.jdk.thread.demo1;

import java.io.File;
import java.util.List;

public class FileSearchUtil {

	/**
	 * 根据关键字 String keyword 查询文件名称，并保存在 List<String> lists中，
	 * 
	 * @param keyword
	 *            查询关键字
	 * @param pathName
	 *            需要寻找的路径名
	 */
	public static void searchByKeywords(List<String> lists, String keyword, String pathName) {
		File file = new File(pathName);

		// 若该文件是目录
		if (file.isDirectory()) {

			// System.out.println(file.getName());

			// 获取所有的子文件
			File[] subFiles = file.listFiles();

			// 遍历
			for (File subFile : subFiles) {
				// To get the file's name and DFS
				searchByKeywords(lists, keyword, subFile.getAbsolutePath());
			}
		} else {// 若是文件
			String name = file.getName();

			// 文件名是否包含关键字
			if (name.contains(keyword)) {

				// save the absolute path into List<String>
				lists.add(file.getAbsolutePath());
			}
		}
	}
}
