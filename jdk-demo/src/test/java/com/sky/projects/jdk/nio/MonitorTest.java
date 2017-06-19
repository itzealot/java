package com.sky.projects.jdk.nio;

public class MonitorTest {

	public static void main(String[] args) throws Exception {
		// -Xms10M -Xmx10M -XX:MaxDirectMemorySize=10M
		System.out.println(Monitor.monitorDirectMemory());
	}
}
