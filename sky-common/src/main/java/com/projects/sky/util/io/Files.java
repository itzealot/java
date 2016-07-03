package com.projects.sky.util.io;

import static com.projects.sky.util.common.Closeables.close;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.google.common.collect.Lists;
import com.projects.sky.util.base.Threads;

/**
 * File Util
 * 
 * @author zt
 */
public final class Files {

	public static void appendWithBufferedWriter(String file, String content) {
		appendWithBufferedWriter(file, Arrays.asList(content));
	}

	public static void appendWithBufferedWriter(String file, List<String> contents) {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));

			for (String content : contents) {
				out.write(content);
				out.write("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(out);
		}
	}

	public static void appendWithFileWriter(String file, List<String> contents) {
		FileWriter writer = null;

		try {
			writer = new FileWriter(file, true);
			for (String content : contents) {
				writer.write(content);
				writer.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(writer);
		}
	}

	public static void appendWithFileWriter(String file, String content) {
		appendWithFileWriter(file, Arrays.asList(content));
	}

	public static void appendWithRandomAccessFile(String file, String content) {
		appendWithRandomAccessFile(file, Arrays.asList(content));
	}

	public static void appendWithRandomAccessFile(String file, List<String> contents) {
		RandomAccessFile randomFile = null;

		try {
			// 按读写方式打开一个随机访问文件流
			randomFile = new RandomAccessFile(file, "rw");
			// 文件长度，字节数,将写文件指针移到文件尾。
			randomFile.seek(randomFile.length());

			for (String content : contents) {
				randomFile.writeBytes(content);
				randomFile.writeChar('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(randomFile);
		}
	}

	/**
	 * read file content to BlockingQueue, when the queue's size is beyond
	 * queueSizeLimit, then sleep millis(ms)
	 * 
	 * 从文件中读取内容到阻塞队列中，如果读取的内容超过阻塞队列配置的大小，则休息指定时间(ms)
	 * 
	 * @param file
	 * @param queue
	 * @param queueSizeLimit
	 * @param millis
	 */
	public static void read(File file, BlockingQueue<String> queue, int queueSizeLimit, long millis) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = reader.readLine()) != null) {
				queue.add(line);

				if (queue.size() >= queueSizeLimit) {
					Threads.sleep(millis);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(reader);
		}
	}

	/**
	 * 从阻塞队列中获取 lineCounts 条记录写入到文件中
	 * 
	 * @param file
	 * @param queue
	 * @param lineCounts
	 */
	public static void write(File file, BlockingQueue<String> queue, int lineCounts) {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(file));

			List<String> lines = Lists.newLinkedList();
			queue.drainTo(lines, lineCounts);

			for (String line : lines)
				writer.write(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(writer);
		}
	}

	private Files() {
	}
}
