package com.apusic.skynet.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;

import com.apusic.skynet.zookeeper.api.ZkRoot;

/**
 * ZkConnection
 * 
 * @author zt
 *
 */
public final class ZkConnection {

	// The persistence Zookeeper client
	protected CuratorFramework client;

	/**
	 * To create persistence Zookeeper Connection
	 * 
	 * @param client
	 */
	protected ZkConnection(CuratorFramework client) {
		this.client = client;
	}

	/**
	 * To close the Zookeeper Connection
	 */
	public void close() {
		if (client != null) {
			client.close();
		}
	}

	/**
	 * To start the Zookeeper
	 */
	public void start() {
		if (client != null && client.getState() != CuratorFrameworkState.STARTED) {
			client.start();
		}
	}

	public void inTransaction() {
		if (client != null) {
			client.inTransaction();
		}
	}

	/**
	 * To get the Root object
	 * 
	 * @return
	 */
	public ZkRoot getRoot() {
		this.start();
		return ZkRoot.getInstance(this);
	}

	public CuratorFramework getClient() {
		return client;
	}
}
