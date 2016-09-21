package com.sky.projects.design.mw;

import java.util.Map;
import java.util.Set;

/**
 * Master-Worker 程序入口
 * 
 * @author zealot
 */
public class Main {

	public static void main(String[] args) {
		// 使用 5 个 Worker 运行任务
		Master master = new Master(new PlusWorker(), 5);

		// 向 Master 提交 100 个子任务
		for (int i = 0; i < 100; i++) {
			master.submit(i);
		}

		// 执行任务
		master.execute();

		Map<String, Object> results = master.getResults();

		int sum = 0;
		// 未完成任务
		while (results.size() > 0 || !master.isComplete()) {
			Set<String> keys = results.keySet();

			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}

			Integer i = null;
			if (key != null) {
				i = (Integer) results.get(key);
			}

			if (i != null) {// 结果累加
				sum += i;
			}

			if (key != null) {// 移除已经计算过的项
				results.remove(key);
			}
		}

		System.out.println("sum = " + sum);
	}
}
