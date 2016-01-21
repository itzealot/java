package com.apusic.skynet.zookeeper;

import org.apache.curator.framework.CuratorTempFramework;

public class ZkTempConnection {
	private CuratorTempFramework client;

	public ZkTempConnection(CuratorTempFramework client) {
		this.client = client;
	}

	public void close() {
		if (client != null) {
			client.close();
		}
	}

	public void inTransaction() {
		if (client != null) {
			try {
				client.inTransaction();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
