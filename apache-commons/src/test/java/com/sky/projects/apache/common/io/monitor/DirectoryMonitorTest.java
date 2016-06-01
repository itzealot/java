package com.sky.projects.apache.common.io.monitor;

public class DirectoryMonitorTest {

	public static void main(String[] args) throws Exception {
		// 每 5 秒扫描指定路径下文件的变化
		DirectoryMonitor m = new DirectoryMonitor(5000);

		// 设置监听器
		m.monitor("E:/monitor", new DirectoryListener());
		m.start();
	}
}
