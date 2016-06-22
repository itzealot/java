package com.sky.projects.jdk.thread.traditional;

/**
 * 任务类 Task
 * 
 * @author zt
 */
public class Task {

	/**
	 * 执行任务
	 * 
	 * @param name
	 * @param counts
	 */
	public static void excute(String name, int counts) {
		for (int i = 1; i <= counts; i++) {
			System.out.println("Thread name is : " + name + ", " + i);
		}
		System.out.println();
	}
}