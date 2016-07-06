package com.projects.sky.util.common;

/**
 * AutoCloseable Util
 * 
 * @author zt
 */
public final class Closeables {

	public static void close(AutoCloseable... closes) {
		if (closes != null)
			for (int i = 0, len = closes.length; i < len; i++) {
				try {
					if (closes[i] != null) {
						closes[i].close();
					}
				} catch (Exception e) {
				} finally {
					closes[i] = null;
				}
			}
	}

	private Closeables() {
	}
}
