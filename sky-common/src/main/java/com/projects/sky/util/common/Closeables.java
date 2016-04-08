package com.projects.sky.util.common;

/**
 * 关闭流
 * 
 * @author zt
 */
public final class Closeables {
	private Closeables() {
	}

	public static void close(AutoCloseable... closes) {
		if (closes == null) {
			return;
		}

		int length = closes.length;

		for (int i = 0; i < length; i++) {
			try {
				if (closes[i] != null)
					closes[i].close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
