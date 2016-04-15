package com.sky.projects.redis.pipeline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class MacCountNew {
	private static String url = "C:\\Users\\Administrator\\Desktop\\mac-15001.txt";
	private static String counts = "C:\\Users\\Administrator\\Desktop\\mac-counts-15001.txt";

	static Jedis jedis = new Jedis("localhost", 6380);

	public static void main(String[] args) {
		dealMAC();
	}

	public static void appendInto(String file, List<Result> ress) {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));

			for (Result r : ress) {
				out.write(r.toString());
				out.write("\n");
			}

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

	public static void appendInto(String file, Result re) {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));

			out.write(re.toString());
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

				if (macs.size() >= 200) {

					List<Result> ress = dealMap(macs);

					new Thread(new WriteThread(counts, ress)).start();

					macs.clear();
				}

				str = br.readLine();
			}

			// 最后剩余的写入
			List<Result> ress = pipelined(macs);
			new Thread(new WriteThread(counts, ress)).start();

			macs.clear();

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

	public static void dealMAC2() {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(url);
			br = new BufferedReader(fr);

			String str = br.readLine();

			while (str != null) {
				appendInto(counts, pipelined(str));

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

	/**
	 * 批量获取redis Hash类型的所有记录
	 * 
	 * @param key
	 *            redis中的key
	 */
	public static Map<String, String> hgetAll(final String key) {
		Map<String, String> returnMap = null;

		try {
			Pipeline pipeline = jedis.pipelined();
			Response<Map<String, String>> response = pipeline.hgetAll(key);
			pipeline.sync();

			returnMap = response.get();
		} catch (Exception e) {
		} finally {

		}

		return returnMap;
	}

	public static List<Result> dealMap(List<String> macs) {
		List<Result> results = new ArrayList<>();

		for (String mac : macs) {

			System.out.println("mac :" + mac);

			if (mac.length() <= 5) {
				results.add(new Result(mac, "0"));
			}

			String key = "m_" + mac.substring(0, 5);
			String hashKey = mac.substring(5);

			Map<String, String> map = hgetAll(key);

			String value = map.get(hashKey);

			if (value == null) {
				results.add(new Result(mac, "0"));
				continue;
			}

			String[] arrays = value.split("|");

			// 处理获取的值
			if (arrays == null || arrays.length == 0) {
				results.add(new Result(mac, "0"));
			} else {
				results.add(new Result(mac, arrays[0]));
			}
		}

		return results;
	}

	public static List<Result> pipelined(List<String> macs) {
		List<Result> results = new ArrayList<Result>();

		for (String mac : macs) {
			if (mac.length() <= 5) {
				results.add(new Result(mac, "0"));
			}

			String key = "m_" + mac.substring(0, 5);
			String hashKey = mac.substring(5);

			Pipeline pipeline = jedis.pipelined();
			Response<Map<String, String>> response = pipeline.hgetAll(key);
			jedis.sync();

			Map<String, String> res = response.get();

			String value = res.get(hashKey);

			if (value == null) {
				results.add(new Result(mac, "0"));
				continue;
			}

			String[] arrays = value.split("|");

			// 处理获取的值
			if (arrays == null || arrays.length == 0) {
				results.add(new Result(mac, "0"));
			} else {
				results.add(new Result(mac, arrays[0]));
			}
		}

		return results;
	}

	public static Result pipelined(String mac) {
		if (mac.length() <= 5) {
			return new Result(mac, "0");
		}

		String key = "m_" + mac.substring(0, 5);
		String hashKey = mac.substring(5);

		Pipeline pipeline = jedis.pipelined();
		Response<Map<String, String>> response = pipeline.hgetAll(key);
		jedis.sync();

		Map<String, String> res = response.get();

		String value = res.get(hashKey);

		if (value == null) {
			return new Result(mac, "0");
		}

		String[] arrays = value.split("|");

		// 处理获取的值
		if (arrays == null || arrays.length == 0) {
			return new Result(mac, "0");
		}

		return new Result(mac, arrays[0]);
	}
}
