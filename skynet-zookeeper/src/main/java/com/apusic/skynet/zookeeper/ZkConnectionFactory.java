package com.apusic.skynet.zookeeper;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * ZookeeperConnection 工厂类
 * 
 * @author zt
 *
 */
public class ZkConnectionFactory {
	private static final int BASE_SLEEP_TIME_MS = 1000;
	private static final int MAX_RETRIES = 3;
	private static final ExponentialBackoffRetry DEFAULT_RETRY_POLICY = new ExponentialBackoffRetry(BASE_SLEEP_TIME_MS,
			MAX_RETRIES);
	private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 3000;
	private static final int DEFAULT_SESSION_TIMEOUT_MS = 3000;
	private static final boolean CAN_BE_READ_ONLY = false;
	private static final byte[] DEFAULT_DATA = null;

	/**
	 * To create by connectionString
	 * 
	 * @param connectionString
	 * @return
	 */
	public static ZkConnection create(String connectionString) {
		return new ZkConnection(CuratorFrameworkFactory.newClient(connectionString, DEFAULT_RETRY_POLICY));
	}

	/**
	 * To create by connectionString and namespace
	 * 
	 * @param connectionString
	 * @param namespace
	 * @return
	 */
	public static ZkConnection create(String connectionString, String namespace) {
		return create(connectionString, namespace, DEFAULT_CONNECTION_TIMEOUT_MS, DEFAULT_SESSION_TIMEOUT_MS,
				DEFAULT_RETRY_POLICY, CAN_BE_READ_ONLY, DEFAULT_DATA);
	}

	public static ZkConnection create(String connectionString, String namespace, int connectionTimeoutMs,
			int sessionTimeoutMs) {
		return create(connectionString, namespace, connectionTimeoutMs, sessionTimeoutMs, DEFAULT_RETRY_POLICY,
				CAN_BE_READ_ONLY, DEFAULT_DATA);
	}

	private static ZkConnection create(String connectionString, String namespace, int connectionTimeoutMs,
			int sessionTimeoutMs, ExponentialBackoffRetry retryPolicy, boolean canBeReadOnly, byte[] defaultData) {
		return new ZkConnection(createCuratorFramework(connectionString, namespace, connectionTimeoutMs,
				sessionTimeoutMs, retryPolicy, canBeReadOnly, defaultData));
	}

	private static CuratorFramework createCuratorFramework(String connectionString, String namespace,
			int connectionTimeoutMs, int sessionTimeoutMs, ExponentialBackoffRetry retryPolicy, boolean canBeReadOnly,
			byte[] defaultData) {
		return CuratorFrameworkFactory.builder().connectString(connectionString)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).retryPolicy(retryPolicy)
				.canBeReadOnly(canBeReadOnly).namespace(namespace).defaultData(defaultData).build();
	}

	/**
	 * To create temp Connection
	 * 
	 * @return
	 */
	public static ZkTempConnection createTemp() {
		return new ZkTempConnection(CuratorFrameworkFactory.builder().buildTemp());
	}

	/**
	 * To create temp Connection by inactiveThreshold and TimeUnit
	 * 
	 * @param inactiveThreshold
	 * @param unit
	 * @return
	 */
	public static ZkTempConnection createTemp(long inactiveThreshold, TimeUnit unit) {
		return new ZkTempConnection(CuratorFrameworkFactory.builder().buildTemp(inactiveThreshold, unit));
	}
}
