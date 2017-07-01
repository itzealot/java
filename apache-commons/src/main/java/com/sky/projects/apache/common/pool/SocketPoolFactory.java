package com.sky.projects.apache.common.pool;

import java.io.Closeable;
import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

/**
 * ConnectionPoolFactory
 * 
 * @author zealot
 *
 */
public class SocketPoolFactory {

	// 试用对象池
	private final GenericObjectPool<Socket> pool;

	public SocketPoolFactory(Config config, String ip, int port) {
		pool = new GenericObjectPool<Socket>(new SocketFactory(ip, port), config);
	}

	public Socket get() {
		try {
			return pool != null ? pool.borrowObject() : null;
		} catch (Exception e) {
			return null;
		}
	}

	public void release(Socket socket) {
		if (socket == null) {
			return;
		}
		try {
			pool.returnObject(socket);
		} catch (Exception e) {
		} finally {
			close(socket);
		}
	}

	private void close(Closeable clo) {
		if (clo != null) {
			try {
				clo.close();
			} catch (Exception ex) {
			}
		}
	}
}