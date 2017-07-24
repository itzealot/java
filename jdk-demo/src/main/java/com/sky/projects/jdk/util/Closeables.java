package com.sky.projects.jdk.util;

/**
 * Closeables
 * 
 * @author zealot
 */
public final class Closeables {

	public static void close(AutoCloseable... cs) {
		if (cs != null) {
			for (AutoCloseable c : cs)
				try {
					if (c != null)
						c.close();
				} catch (Exception e) {
				}
		}
	}

	private Closeables() {
	}
}
