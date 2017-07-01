package com.sky.projects.apache.common.pool;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * 对象工厂，即包含对对象的基本操作(create, destroy, validate)
 * 继承{@link BasePoolableObjectFactory}
 * 
 * @author zealot
 *
 */
public class SocketFactory extends BasePoolableObjectFactory<Socket> {

	private final InetSocketAddress address;

	public SocketFactory(String ip, int port) {
		address = new InetSocketAddress(ip, port);
	}

	@Override
	public Socket makeObject() throws Exception {
		Socket socket = new Socket();
		socket.connect(address);
		return socket;
	}

	@Override
	public void destroyObject(Socket obj) throws Exception {
		if (obj != null)
			obj.close();
	}

	@Override
	public boolean validateObject(Socket socket) {
		return socket != null ? socket.isConnected() && !socket.isClosed() : false;
	}

}