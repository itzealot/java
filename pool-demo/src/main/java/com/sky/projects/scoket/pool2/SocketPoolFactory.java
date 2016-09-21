package com.sky.projects.scoket.pool2;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class SocketPoolFactory implements PooledObjectFactory<Socket>, Serializable {

	private static final long serialVersionUID = 343602361666189120L;

	private InetSocketAddress address;

	public SocketPoolFactory(String ip, int port) {
		address = new InetSocketAddress(ip, port);
	}

	@Override
	public PooledObject<Socket> makeObject() throws Exception {
		Socket socket = new Socket();
		socket.connect(address);
		return new DefaultPooledObject<Socket>(socket);
	}

	@Override
	public void destroyObject(PooledObject<Socket> p) throws Exception {
		Socket Socket = p.getObject();
		if (Socket != null) {
			Socket.close();
			Socket = null;
		}
	}

	@Override
	public boolean validateObject(PooledObject<Socket> p) {
		Socket Socket = p.getObject();
		return Socket == null ? false : !Socket.isClosed();
	}

	@Override
	public void activateObject(PooledObject<Socket> p) throws Exception {
	}

	@Override
	public void passivateObject(PooledObject<Socket> p) throws Exception {
	}

}
