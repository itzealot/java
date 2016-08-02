package com.sky.projects.pool;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * <p>
 * 对象工厂，即包含对对象的基本操作(create, destroy, validate).
 * </p>
 * <p>
 * 继承 {@link BasePoolableObjectFactory}
 * </p>
 * 
 * @author zt
 *
 */
public class ConnectionFactory extends BasePoolableObjectFactory<Socket> {

	private InetSocketAddress address;

	public ConnectionFactory(String ip, int port) {
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
		return socket.isConnected() && !socket.isClosed();
	}

}