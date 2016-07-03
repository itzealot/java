package com.projects.sky.util.common;

public final class Closeables {
	private Closeables() {
	}

	public static void close(AutoCloseable... closes) {
		if (closes != null)
			for (int i = 0, len = closes.length; i < len; i++) {
				try {
					if (closes[i] != null) {
						closes[i].close();
						closes[i] = null;
					}
				} catch (Exception e) {
					closes[i] = null;
				}
			}
	}
}
