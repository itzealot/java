package com.sky.projects.scoket.pool2;

import java.io.Serializable;
import java.net.Socket;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class SocketPool implements Serializable {

	private static final long serialVersionUID = -7807621334691538052L;

	private static SocketPool instance = null;

	// 试用对象池
	private static GenericObjectPool<Socket> pool;

	private SocketPool() {
	}

	public static SocketPool getInstance(GenericObjectPoolConfig config, String ip, int port) {
		if (instance == null) {
			synchronized (SocketPool.class) {
				instance = new SocketPool();
				pool = new GenericObjectPool<Socket>(new SocketPoolFactory(ip, port), config);
			}
		}

		return instance;
	}

	public Socket getSocket() throws RuntimeException {
		try {
			return pool.borrowObject();
		} catch (Exception e) {
			throw new RuntimeException("Could not get a Socket from the pool", e);
		}
	}

	public void returnSocket(Socket socket) throws RuntimeException {
		if (null != socket)
			try {
				pool.returnObject(socket);
			} catch (Exception e) {
				throw new RuntimeException("Could not return the Socket to the pool", e);
			}
	}

	public void invalidateSocket(Socket socket) throws RuntimeException {
		if (socket != null)
			try {
				pool.invalidateObject(socket);
			} catch (Exception e) {
				throw new RuntimeException("Could not invalidate the Socket to the pool", e);
			}
	}

}
