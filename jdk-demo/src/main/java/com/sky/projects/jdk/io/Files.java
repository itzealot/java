package com.sky.projects.jdk.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.List;

import com.google.common.collect.Lists;

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

	/**
	 * 返回 path 路径及子路径的文件
	 * 
	 * @param path
	 * @param suffix
	 * @return
	 */
	public static List<File> listFiles(String path, String suffix) {
		if (path == null || path.isEmpty()) {
			return null;
		}

		File file = new File(path);

		if (file.exists()) {
			List<File> results = Lists.newArrayList();
			if (file.isDirectory()) { // path为目录
				File[] files = file.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.isFile() && pathname.getName().endsWith(suffix);
					}
				});

				if (files != null && files.length != 0)
					append(results, files);

				// 获取path的所有子目录
				File[] dirs = file.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.isDirectory();
					}
				});

				// 遍历所有子目录，获取对应的后缀文件
				for (int i = 0, len = dirs.length; i < len; i++) {
					List<File> subFiles = listFiles(dirs[i].getAbsolutePath(), suffix);

					if (subFiles != null && !subFiles.isEmpty())
						results.addAll(subFiles);
				}

				return results;
			} else if (file.isFile() && path.endsWith(suffix)) {
				results.add(file);
				return results;
			}
		}

		return null;
	}

	private static void append(List<File> results, File[] files) {
		for (int i = 0, len = files.length; i < len; i++) {
			results.add(files[i]);
		}
	}

	private static void close(AutoCloseable... acs) {
		if (acs != null) {
			for (AutoCloseable ac : acs) {
				if (ac != null) {
					try {
						ac.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}
}
