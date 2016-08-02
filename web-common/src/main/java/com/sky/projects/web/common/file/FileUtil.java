package com.sky.projects.web.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class FileUtil {

	public static List<String> read(List<File> files) {
		List<String> results = new ArrayList<>();
		for (File file : files) {
			results.addAll(read(file));
		}
		return results;
	}

	public static List<String> read(File file) {
		return read(file, Charset.forName("UTF-8"));
	}

	public static List<String> read(File file, Charset ch) {
		List<String> results = new ArrayList<>();

		InputStream in = null;
		BufferedReader br = null;

		try {
			in = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(in, ch));
			String line = null;
			while ((line = br.readLine()) != null) {
				results.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(br, in);
		}

		return results;
	}

	public static void close(AutoCloseable... closeables) {
		if (closeables != null)
			for (int i = 0, len = closeables.length; i < len; i++)
				if (closeables[i] != null) {
					try {
						closeables[i].close();
					} catch (Exception e) {
					} finally {
						closeables[i] = null;
					}
				}

	}

	private FileUtil() {
	}
}
