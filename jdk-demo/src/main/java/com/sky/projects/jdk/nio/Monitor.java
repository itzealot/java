package com.sky.projects.jdk.nio;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Monitor
 * 
 * @author zealot
 *
 */
public final class Monitor {

	/**
	 * monitorDirectMemory
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Long> monitorDirectMemory() throws Exception {
		// 仅包内可见，需要反射获取
		Class<?> clazz = Class.forName("java.nio.Bits");

		// 获取私有变量
		Field maxMemory = clazz.getDeclaredField("maxMemory");
		maxMemory.setAccessible(true);

		Field reservedMemory = clazz.getDeclaredField("reservedMemory");
		reservedMemory.setAccessible(true);

		Map<String, Long> results = new HashMap<>();

		synchronized (clazz) {
			Long maxMemoryValue = (Long) maxMemory.get(null);
			Long reservedMemoryValue = (Long) reservedMemory.get(null);

			results.put("maxMemory", maxMemoryValue);
			results.put("reservedMemory", reservedMemoryValue);
		}

		return results;
	}

	private Monitor() {
	}
}
