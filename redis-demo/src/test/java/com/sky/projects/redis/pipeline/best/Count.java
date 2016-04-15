package com.sky.projects.redis.pipeline.best;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.projects.sky.util.common.Closeables;
import com.sky.projects.redis.pipeline.Result;
import com.sky.projects.redis.pipeline.WriteThread;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class Count {

	private static String url = "C:\\Users\\Administrator\\Desktop\\mac-all-new.txt";
	private static String counts = "C:\\Users\\Administrator\\Desktop\\mac-counts-all-new2.txt";

	static Jedis jedis = new Jedis("localhost", 6380);

	public static void main(String[] args) {
		dealMAC();
	}

	public static void dealMAC() {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(url);
			br = new BufferedReader(fr);

			String str = br.readLine();
			List<String> macs = new ArrayList<>();

			while (str != null) {
				macs.add(str);

				if (macs.size() >= 1000) {
					System.out.println("start deal........");
					List<Result> ress = hgetResults(macs);

					System.out.println("start write........");
					new Thread(new WriteThread(counts, ress)).start();

					macs.clear();
				}

				str = br.readLine();
			}

			// 最后剩余的写入
			List<Result> ress = hgetResults(macs);
			new Thread(new WriteThread(counts, ress)).start();

			macs.clear();

		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			Closeables.close(br, fr);
		}
	}

	public static List<Response<String>> hgetResponses(List<String> macs) {
		List<Response<String>> lists = new ArrayList<>();

		Pipeline pipeline = jedis.pipelined();

		for (String mac : macs) {
			if (mac.equals("") || mac.length() <= 5) {
				lists.add(null);
				continue;
			}

			String key = "m_" + mac.substring(0, 5);
			String hashKey = mac.substring(5);

			System.out.println("key = " + key + ", hashKey = " + hashKey);

			Response<String> response = pipeline.hget(key, hashKey);

			lists.add(response);
		}

		pipeline.sync();

		return lists;
	}

	public static List<Result> hgetResults(List<String> macs) {
		List<Response<String>> lists = hgetResponses(macs);

		List<Result> results = new ArrayList<>();

		int len = macs.size();

		for (int i = 0; i < len; i++) {
			Response<String> response = lists.get(i);
			String mac = macs.get(i);

			if (response == null) {
				results.add(new Result(mac, "0"));
				continue;
			}

			String value = response.get();

			if (value == null) {
				results.add(new Result(mac, "0"));
				continue;
			}

			String[] arrays = value.split("\\|");

			// 处理获取的值
			if (arrays == null || arrays.length == 0) {
				results.add(new Result(mac, "0"));
			} else {
				results.add(new Result(mac, arrays[0]));
			}
		}

		return results;
	}

}
