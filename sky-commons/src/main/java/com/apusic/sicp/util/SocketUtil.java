package com.apusic.sicp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SocketUtil {

	private static final Log log = LogFactory.getLog(SocketUtil.class);

	public static boolean isConnection(String ip, int port) {
		Socket socket = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			SocketAddress socketAddress = new InetSocketAddress(ip, port);
			socket = new Socket();
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
			log.warn("server ip = " + ip + " :port = " + port + "连接不成功");
			return false;
		} finally {
			ResourceUtil.close(inputStream, outputStream);
			ResourceUtil.close(socket);
		}
	}
}
