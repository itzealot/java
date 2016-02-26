package com.zt.test.common;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class Closes {
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
				// TODO
			}
		}
	}

	public static void close(Socket socket) {
		if (socket == null) {
			return;
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO
		}
	}
}
