package com.sky.projects.jdk.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.List;

public final class Files {

	private Files() {
	}

	public static void appendWithBufferedWriter(String file, String content) {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			out.write(content);
			out.write("\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(out);
		}
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
		FileWriter writer = null;

		try {
			writer = new FileWriter(file, true);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(writer);
		}
	}

	public static void appendWithRandomAccessFile(String file, String content) {
		RandomAccessFile randomFile = null;

		try {
			// 打开一个随机访问文件流，按读写方式
			randomFile = new RandomAccessFile(file, "rw");

			// 文件长度，字节数
			long fileLength = randomFile.length();

			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);

			randomFile.writeBytes(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(randomFile);
		}
	}

	public static void appendWithRandomAccessFile(String file, List<String> contents) {
		RandomAccessFile randomFile = null;

		try {
			randomFile = new RandomAccessFile(file, "rw");

			long fileLength = randomFile.length();

			randomFile.seek(fileLength);

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

	private static void close(AutoCloseable... clos) {
		if (clos != null) {
			for (AutoCloseable c : clos) {
				if (c != null) {
					try {
						c.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						c = null;
					}
				}
			}
		}
	}
}
