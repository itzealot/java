package com.sky.projects.jdk.jvm;

public class TestHeapGC {

	/**
	 * -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
	 * -Xms40M -Xmx40M -Xmn20M
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] b1 = new byte[1024 * 1024 / 2];
		byte[] b2 = new byte[1024 * 1024 * 8];

		b2 = null;

		b2 = new byte[1024 * 1024 * 8];// 进行一次新生代 GC

		// 调用，未被回收的对象全部被移入老年代
//		System.gc();
	}
}
