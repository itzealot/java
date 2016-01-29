package com.zt.test.thread.test1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 处理文件中的文本
 * 
 * @author zengtao
 *
 */
public class DealFileText {

	// 要处理的文件
	private File file = null;

	// 线程数数组
	private CountWordsThread[] threads = null;

	// 线程List
	private List<Thread> listThread = null;

	public DealFileText(File file) {
		this.file = file;
	}

	/**
	 * 构造函数
	 * 
	 * @param file
	 *            the file must be deal
	 * @param threadNum
	 *            the CountWordsThread thread counts
	 */
	public DealFileText(File file, int threadNum) {
		this.file = file;
		this.threads = new CountWordsThread[threadNum];
	}

	/**
	 * 处理文件
	 */
	public void dealFile() {

		// 获取文件长度
		long length = file.length();

		// The thread-1
		final CountWordsThread thread1 = new CountWordsThread(file, 0,
				length / 2);

		// The thread-2
		final CountWordsThread thread2 = new CountWordsThread(file, length / 2,
				length);

		// new Thread
		final Thread t1 = new Thread(thread1);
		final Thread t2 = new Thread(thread2);

		// 开启两个线程分别处理文件的不同片段
		t1.start();
		t2.start();

		// The main Thread
		Thread mainThread = new Thread() {

			// 主线程来监听子线程是否运行完成
			public void run() {

				// 一直运行，直至两个线程都运行完成
				while (true) {
					// 两个线程均运行结束,State.TERMINATED
					if (Thread.State.TERMINATED == t1.getState()
							&& Thread.State.TERMINATED == t2.getState()) {

						// 获取各自处理的结果
						Map<String, Integer> hMap1 = thread1.getResultMap();
						Map<String, Integer> hMap2 = thread2.getResultMap();

						// 使用TreeMap保证结果有序
						TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();

						// 对不同线程处理的结果进行整合
						tMap.putAll(hMap1);
						tMap.putAll(hMap2);

						//
						Map<String, Integer> map = maxCountOfCharacters(tMap);

						showMessage(map);

						// 终止线程
						t1.interrupt();
						t2.interrupt();
						return;
					}
				}
			}
		};

		// 启动主线程
		mainThread.start();
	}

	/**
	 * 创建线程List<Thread>
	 */
	public void createThreads() {
		listThread = new ArrayList<Thread>();

		int threadLength = this.threads.length;
		long fileLength = this.file.length();

		// 每线程应该读取的字节数
		long numPerThred = fileLength / threadLength;

		// 整个文件整除后剩下的余数
		long left = fileLength % threadLength;

		Thread tempThread = null;
		for (int i = 0; i < threadLength; i++) {
			// 让每个线程分别负责读取文件的不同部分。
			try {
				// 最后一个线程读取指定numPerThred + left个字节
				if (i == threadLength - 1) {
					this.threads[i] = new CountWordsThread(file, i
							* numPerThred, (i + 1) * numPerThred + left);
				} else {// 每个线程负责读取一定的numPerThred个字节
					this.threads[i] = new CountWordsThread(file, i
							* numPerThred, (i + 1) * numPerThred);
				}
				tempThread = new Thread(threads[i]);
				tempThread.start();
				listThread.add(tempThread);
			} catch (Exception e) {
				System.out.println("系统出现严重错误！系统将终止运行！");
				e.printStackTrace();
				// 系统退出
				System.exit(-1);
			}
		}
	}

	public List<CountWordsThread> doThread(CountWordsThread thread) {
		List<CountWordsThread> list = new ArrayList<CountWordsThread>();
		list.add(thread);
		return list;
	}

	public static Map<String, Integer> maxCountOfCharacters(
			Map<String, Integer> chineseCounts) {
		if (chineseCounts == null) {
			return null;
		}
		Set<String> keys = chineseCounts.keySet();
		Iterator<String> iterator = keys.iterator();
		int max = 0;
		Map<String, Integer> result = new HashMap<String,

		Integer>();
		while (iterator.hasNext()) {
			String currChar = iterator.next();
			int currCount = chineseCounts.get(currChar);
			if (currCount > max) {
				max = currCount;
				result.clear();
				result.put(currChar, max);
			} else if (currCount == max) {
				result.put(currChar, max);
			}
		}
		return result;
	}

	/**
	 * To show the Map<String, Integer> map's info
	 * 
	 * @param map
	 */
	public static void showMessage(Map<String, Integer> map) {
		if (null != map && map.size() > 0) {
			Set<String> keys = map.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				System.out.println("出现次数最多的人是[ " + key + "]" + "  次数为：["
						+ map.get(key) + "]");
			}
		}
	}
}
