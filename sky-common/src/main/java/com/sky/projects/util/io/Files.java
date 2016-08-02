package com.sky.projects.util.io;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.sky.projects.util.common.Closeables.close;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sky.projects.util.base.Threads;

/**
 * File Util
 * 
 * @author zt
 */
public final class Files {

	private static final Logger LOG = LoggerFactory.getLogger(Files.class);

	/**
	 * 处理单个文件，读取内容到阻塞队列中,阻塞队列数据量到达 counts 数量时，休息 sleep ms(用于降低cpu)
	 * 
	 * @param queue
	 * @param file
	 * @param sleep
	 * @param counts
	 * @throws Exception
	 */
	public static void read(BlockingQueue<String> queue, File file, long sleep, int counts) throws Exception {
		BufferedReader reader = null;
		long index = 0;// 记录行号

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = reader.readLine()) != null) {
				queue.put(line);
				index++;

				if (queue.size() >= counts) {
					LOG.info("read thread sleep, current line is : {}, current queue size: {}.", index, queue.size());
					Threads.sleep(sleep);
				}
			}

			LOG.info("finish read all lines from file, all line counts are : {}", index);
		} catch (Exception e) {
			LOG.error("read file contents line by line into BlockingQueue error, line: {}, {}", index, e);
		} finally {
			close(reader);
		}
	}

	/**
	 * 从第 lineStart 行开始处理单个文件，读取内容到阻塞队列中,阻塞队列数据量到达 counts 数量时，休息 sleep
	 * ms(用于降低cpu)
	 * 
	 * @param queue
	 * @param file
	 * @param sleep
	 * @param counts
	 * @param lineStart
	 */
	public static void read(BlockingQueue<String> queue, File file, long sleep, int counts, int lineStart) {
		BufferedReader reader = null;
		long index = 0;// 记录行号

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			// 指向要读的第多少行，且每读 10000 行休息，降低 CPU
			while ((line = reader.readLine()) != null && index < lineStart) {
				index++;
				if (index % 10000 == 0) {
					Threads.sleep(sleep);
				}
			}

			LOG.info("current line is : {}, and start read other line and put into BlockingQueue.", index);

			while ((line = reader.readLine()) != null) {
				queue.put(line);
				index++;

				if (queue.size() >= counts) {
					LOG.info("read thread sleep, current line is : {}, current queue size: {}.", index, queue.size());
					Threads.sleep(sleep);
				}
			}

			LOG.info("finish read all lines from file, all line counts are : {}", index);
		} catch (Exception e) {
			LOG.error("read file contents line by line into BlockingQueue error, line: {}, {}", index, e);
		} finally {
			close(reader);
		}
	}

	/**
	 * append lines into file with BufferedWriter
	 * 
	 * @param file
	 * @param line
	 */
	public static void append(String file, String line) {
		append(file, Arrays.asList(line));
	}

	/**
	 * append lines into file with BufferedWriter
	 * 
	 * @param file
	 * @param line
	 */
	public static void append(File file, String line) {
		append(file, Arrays.asList(line));
	}

	/**
	 * append lines into file with BufferedWriter
	 * 
	 * @param file
	 * @param lines
	 */
	public static void append(String file, List<String> lines) {
		append(new File(file), lines);
	}

	/**
	 * append lines into file with BufferedWriter
	 * 
	 * @param file
	 * @param lines
	 */
	public static void append(File file, List<String> lines) {
		append(file, lines, Charset.forName("UTF-8"));
	}

	/**
	 * append lines into file with BufferedWriter
	 * 
	 * 向文件中追加写入多行数据
	 * 
	 * @param file
	 * @param lines
	 */
	public static void append(final File file, final List<String> lines, Charset charset) {
		BufferedWriter writer = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file, true);
			writer = new BufferedWriter(new OutputStreamWriter(fos, charset));

			for (String line : lines) {
				writer.write(line);
				writer.write("\n");
			}

			LOG.info("finish write contents into file, size : {}", lines.size());
		} catch (IOException e) {
			LOG.error("write contents into file error, path : {}, {}", file.getAbsolutePath(), e);
		} finally {
			close(writer, fos);
		}
	}

	/**
	 * {@link #append(String, List)}
	 * 
	 * @param file
	 * @param lines
	 */
	@Deprecated
	public static void appendWithFileWriter(String file, List<String> lines) {
		FileWriter writer = null;

		try {
			writer = new FileWriter(file, true);
			for (String content : lines) {
				writer.write(content);
				writer.write("\n");
			}
		} catch (IOException e) {
			LOG.error("write contents into file error, file : {}, {}", file, e);
		} finally {
			close(writer);
		}
	}

	/**
	 * {@link #append(String, String)}
	 * 
	 * @param file
	 * @param line
	 */
	@Deprecated
	public static void appendWithFileWriter(String file, String line) {
		appendWithFileWriter(file, Arrays.asList(line));
	}

	public static void appendWithRandomAccessFile(String file, String line) {
		appendWithRandomAccessFile(file, Arrays.asList(line));
	}

	public static void appendWithRandomAccessFile(String file, List<String> lines) {
		RandomAccessFile randomFile = null;

		try {
			// 按读写方式打开一个随机访问文件流
			randomFile = new RandomAccessFile(file, "rw");
			// 文件长度，字节数,将写文件指针移到文件尾。
			randomFile.seek(randomFile.length());

			for (String content : lines) {
				randomFile.writeBytes(content);
				randomFile.writeChar('\n');
			}
		} catch (IOException e) {
			LOG.error("append datas with RandomAccessFile into file error.", e);
		} finally {
			close(randomFile);
		}
	}

	/**
	 * 根据配置的源文件目录和文件后缀扫描目标文件并返回文件目录
	 * 
	 * @param source
	 * @param suffix
	 * @return
	 */
	public static List<File> getSourceFiles(final String source, final String suffix) {
		File file = new File(source);
		if (file.exists()) {// 文件存在
			if (file.isDirectory()) {// 是文件目录
				FileFilter filter = new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.getName().endsWith(suffix);
					}
				};
				return Arrays.asList(file.listFiles(filter));
			} else {
				if (source.endsWith(suffix)) {
					return Arrays.asList(new File(source));
				}
			}
		}

		LOG.error("get source files error, source: {}, suffix: {}", source, suffix);
		throw new IllegalArgumentException("get source files error.");
	}

	/**
	 * 向文件中写入 json 数据
	 * 
	 * @param file
	 * @param datas
	 */
	public static <T> void writeWithJson(final String file, final List<T> datas) {
		writeWithJson(new File(file), datas);
	}

	/**
	 * 向文件中写入 json 数据
	 * 
	 * @param file
	 * @param datas
	 */
	public static <T> void writeWithJson(final File file, final List<T> datas) {
		writeWithJson(file, datas, Charset.forName("UTF-8"));
	}

	/**
	 * 向文件中写入 json 数据
	 * 
	 * @param file
	 * @param datas
	 */
	public static <T> void writeWithJson(final File file, final List<T> datas, Charset ch) {
		checkNotNull(file, "file can not be null");
		checkNotNull(datas, "datas can not be null");

		BufferedWriter writer = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			writer = new BufferedWriter(new OutputStreamWriter(fos, ch));

			String json = new Gson().toJson(datas);
			writer.write(json);
			json = null;
		} catch (Exception e) {
			LOG.error("write datas with json into file error.", e);
		} finally {
			close(writer, fos);
		}

		LOG.info("finish write datas with json into file, size is :{}.", datas.size());
	}

	private Files() {
	}
}
