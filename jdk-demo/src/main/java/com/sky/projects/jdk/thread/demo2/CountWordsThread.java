package com.sky.projects.jdk.thread.demo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 大文件分多个线程处理，此处是单个线程处理的情况，如果需要多个线程，可以创建多个
 * 
 * @author zengtao
 *
 */
public class CountWordsThread implements Runnable {
	/**
	 * 文件通道FileChannel
	 */
	private FileChannel fileChannel = null;

	/**
	 * 文件锁FileLock
	 */
	private FileLock lock = null;
	private MappedByteBuffer mbBuf = null;

	/**
	 * 用于保存结果的 Map<String, Integer> : String is the key, Integer is the String
	 * key counts
	 */
	private Map<String, Integer> hashMap = null;

	/**
	 * 构造函数CountWordsThread(File file, long start, long end)
	 * 
	 * @param file
	 * @param start
	 * @param end
	 */
	@SuppressWarnings("resource")
	public CountWordsThread(File file, long start, long end) {
		try {
			// 得到当前文件的通道且使用RandomAccessFile 对文件进行随机读写
			fileChannel = new RandomAccessFile(file, "rw").getChannel();

			// 锁定当前文件的部分
			lock = fileChannel.lock(start, end, false);

			// 对当前文件片段建立内存映射，如果文件过大需要切割成多个片段
			mbBuf = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, end);

			// 创建HashMap实例存放处理结果
			hashMap = new HashMap<String, Integer>();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重写run方法
	 */
	public void run() {
		String str = Charset.forName("UTF-8").decode(mbBuf).toString();
		StringTokenizer token = new StringTokenizer(str);
		String word = null;

		// 变量所有的
		while (token.hasMoreTokens()) {
			// 将处理结果放到一个HashMap中
			word = token.nextToken().toString().trim();

			Integer number = hashMap.get(word);
			// 判断是否存在
			if (null != number) {
				// 数量 +1
				hashMap.put(word, number + 1);
			} else {
				// 数量初始化为1
				hashMap.put(word, 1);
			}
		}
		try {
			// 释放文件锁
			lock.release();
			// 关闭文件通道
			fileChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取当前线程的执行结果
	 * 
	 * @return To get result map
	 */
	public Map<String, Integer> getResultMap() {
		return hashMap;
	}
}