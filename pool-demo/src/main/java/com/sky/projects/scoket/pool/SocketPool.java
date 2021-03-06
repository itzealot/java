package com.sky.projects.scoket.pool;

import java.io.Closeable;
import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

/**
 * 对象池连接管理工厂
 * 
 * @author zt
 *
 */
public class SocketPool {

	// 试用对象池
	private GenericObjectPool<Socket> pool;

	public SocketPool(Config config, String ip, int port) {
		SocketPoolFactory factory = new SocketPoolFactory(ip, port);

		pool = new GenericObjectPool<Socket>(factory, config);
	}

	public Socket get() throws Exception {
		return pool.borrowObject();
	}

	public void release(Socket socket) {
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
			} finally {
				clo = null;
			}
		}
	}
}