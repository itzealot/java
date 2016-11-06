package com.sky.projects.jdk.jvm;

import com.sky.projects.jdk.thread.Threads;

/**
 * jvm: -Xmx15M -Xms10M -verbose:dc -XX:+PrintGCDetails
 * 
 * @author zealot
 */
public class TraceClassInstance {

	public static void main(String[] args) {
		test();
		test();
	}

	public static void test() {
		byte[] bytes = new byte[10 * 1024 * 1024];

		Threads.sleep(1000);

		System.out.println(bytes);
		bytes = null; // will gc

		Threads.sleep(1000);

		bytes = new byte[10 * 1024];

		System.out.println(bytes);
	}
}
