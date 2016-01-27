package com.apusic.skynet.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Zookeeper 工厂类，用于测试的客户端连接
 * 
 * @author zt
 *
 */
public class ClientFactory {
	public final static String ROOT = "com.apusic.skynet/zookeeper";
	public final static String PATH = "localhost:2181";
	static ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

	public static CuratorFramework newClient() {
		return create(PATH, ROOT);
	}

	public static CuratorFramework create(String connectionString) {
		return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
	}

	public static CuratorFramework create(String connectionString, String namespace) {
		return CuratorFrameworkFactory.builder().connectString(connectionString).connectionTimeoutMs(30000)
				.sessionTimeoutMs(30000).retryPolicy(retryPolicy).canBeReadOnly(false).namespace(namespace)
				.defaultData(null).build();
	}
}
