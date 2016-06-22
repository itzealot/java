package com.sky.projects.jdk.thread.traditional;

/**
 * 多线程之间的通信, 线程1执行10次后，线程2执行50次;线程1执行10次后，线程2执行50次
 * 
 * @author zt
 *
 */
public class TraditionalThreadCommunicationTest {

	public static void main(String[] args) {
		new ThreadManager().execute();
	}
}