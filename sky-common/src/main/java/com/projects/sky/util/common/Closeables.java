package com.projects.sky.util.common;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流
 * 
 * @author zt
 */
public final class Closeables {
	private Closeables() {
	}

	public static void close(Closeable... closes) {
		if (closes == null) {
			return;
		}

		int length = closes.length;

		for (int i = 0; i < length; i++) {
			try {
				if (closes[i] != null)
					closes[i].close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
