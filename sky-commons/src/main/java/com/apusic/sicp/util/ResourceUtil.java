package com.apusic.sicp.util;

import java.io.Closeable;
import java.io.IOException;

public final class ResourceUtil {

	public static void close(Closeable... closeables) {
		if (closeables == null) {
			return;
		}
		for (Closeable item : closeables) {
			try {
				if (item != null) {
					item.close();
				}
			} catch (IOException e) {
			}
		}
	}

	private ResourceUtil() {
	}
}
