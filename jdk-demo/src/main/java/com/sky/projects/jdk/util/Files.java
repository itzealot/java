package com.sky.projects.jdk.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author zealot
 *
 */
public final class Files {

	/**
	 * 
	 * @param file
	 * @param filter
	 * @param suffix
	 */
	public static void genSuffixFile(File file, FileFilter filter, String suffix) {
		if (!file.exists()) {
			return;
		}

		if (file.isDirectory()) { // 是目录
			File[] files = file.listFiles(filter);

			for (File f : files) {
				if (f.isDirectory()) { // 是目录，则递归创建
					genSuffixFile(f, filter, suffix);
				} else if (f.isFile()) {
					createNewFile(f, suffix);
				}
			}
		} else if (file.isFile()) {
			createNewFile(file, suffix);
		}
	}

	private static void createNewFile(File f, String suffix) {
		try {
			new File(f.getAbsolutePath() + suffix).createNewFile();
		} catch (IOException e) {
		}
	}

	public static void genOkFile(File file) {
		genSuffixFile(file, dir -> dir.getName().endsWith(".log") || dir.isDirectory(), ".ok");
	}

	public static void deleteDirFile(File dir) {
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}

		File[] fileList = dir.listFiles();
		if (fileList == null || fileList.length == 0) {
			return;
		}

		for (File file : fileList) {
			if (file.exists() && file.isFile()) {
				FileUtils.deleteQuietly(file);
			}
			if (file.exists() && file.isDirectory()) {
				deleteDirFile(file);
			}
		}
	}

	private Files() {
	}

}
