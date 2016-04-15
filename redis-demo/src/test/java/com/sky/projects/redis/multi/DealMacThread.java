package com.sky.projects.redis.multi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import redis.clients.jedis.Jedis;

public class DealMacThread implements Runnable {
	public static final String PREFFIX = "C:\\Users\\Administrator\\Desktop\\mac-";
	public static final String SUFFIX = ".txt";

	private String source;
	private Jedis redis = null;
	private String target;

	public DealMacThread(Integer offset) {
		source = PREFFIX + offset + SUFFIX;

		target = PREFFIX + "counts-" + offset + SUFFIX;

		redis = new Jedis("localhost", 6381);
	}

	@Override
	public void run() {
		dealMAC();
	}

	public void dealMAC() {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(source);
			br = new BufferedReader(fr);

			String str = br.readLine();
			while (str != null) {
				// 处理非法 MAC
				if (str.length() <= 5) {
					str = br.readLine();
					continue;
				}

				String key = "m_" + str.substring(0, 5);
				String hashKey = str.substring(5);

				String value = redis.hgetAll(key).get(hashKey);
				System.out.println(str + "\t\t" + value);

				// 处理空值
				if (value == null) {
					appendInto(target, str + "\t\t" + 0);
					str = br.readLine();
					continue;
				}

				String[] arrays = value.split("|");

				// 处理获取的值
				if (arrays == null || arrays.length == 0) {
					appendInto(target, str + "\t\t" + 0);
				} else {
					appendInto(target, str + "\t\t" + arrays[0]);
				}

				str = br.readLine();
			}

		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void appendInto(String file, String conent) {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(conent);
			out.write("\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
