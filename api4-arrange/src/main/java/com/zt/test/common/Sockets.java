package com.zt.test.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Sockets {

	public static boolean isConnection(String ip, int port) {
		Socket socket = new Socket();
		OutputStream outputStream = null;
		InputStream inputStream = null;

		try {
			SocketAddress socketAddress = new InetSocketAddress(ip, port);

			socket.connect(socketAddress, 2000);
			socket.setSoTimeout(2000);

			outputStream = socket.getOutputStream();
			outputStream.write("MUX Ping\r\n".getBytes());

			byte[] buf = new byte[5];
			inputStream = socket.getInputStream();
			inputStream.read(buf);

			String line = new String(buf);

			return line.startsWith("+OK");
		} catch (IOException e) {
			// TODO
			System.out.println("server ip = " + ip + ", port = " + port + " can't connect.");
		} finally {
			Closes.close(inputStream, outputStream, socket);
		}

		return false;
	}
}