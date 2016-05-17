package com.zt.test.common;

import java.io.IOException;
import java.net.Socket;

public class Closes {
	public static void close(AutoCloseable... clos) {
		if (clos == null) {
			return;
		}

		for (AutoCloseable item : clos) {
			try {
				if (item != null) {
					item.close();
				}
			} catch (Exception e) {
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
