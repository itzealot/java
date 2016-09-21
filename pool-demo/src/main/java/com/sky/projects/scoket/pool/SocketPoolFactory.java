package com.sky.projects.scoket.pool;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * 对象工厂，即包含对对象的基本操作(create, destroy, validate). 继承
 * {@link BasePoolableObjectFactory}
 * 
 * @author zt
 *
 */
public class SocketPoolFactory extends BasePoolableObjectFactory<Socket> {

	private InetSocketAddress address;

	public SocketPoolFactory(String ip, int port) {
		address = new InetSocketAddress(ip, port);
	}

	public Socket makeObject() throws Exception {
		Socket socket = new Socket();
		socket.connect(address);

		return socket;
	}

	public void destroyObject(Socket obj) throws Exception {
		obj.close();
	}

	public boolean validateObject(Socket socket) {
		if (!socket.isConnected()) {
			return false;
		}

		if (socket.isClosed()) {
			return false;
		}

		return true;
	}

}