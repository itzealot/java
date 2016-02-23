package com.apusic.skynet.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.apusic.skynet.zookeeper.impl.ZkConnectionImpl;

/**
 * 负责持有和Zookeeper的关联，以及维持关联相关的所有必要配置信息
 *
 * @author zt
 *
 */
public class ZkConnectionFactory {
	private static final int BASE_SLEEP_TIME_MS = 1000;
	private static final int MAX_RETRIES = 3;

	private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 3000;
	private static final int DEFAULT_SESSION_TIMEOUT_MS = 3000;

	int connectionTimeoutMs = DEFAULT_CONNECTION_TIMEOUT_MS;
	int sessionTimeoutMs = DEFAULT_SESSION_TIMEOUT_MS;

	ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME_MS, MAX_RETRIES);
	boolean canBeReadOnly = false;
	private String connectionString;
	private String namespace;

	/**
	 * 设置和zookeeper的联系
	 *
	 * @param connectionString
	 * @param namespace
	 */
	public ZkConnectionFactory(String connectionString, String namespace) {
		this.connectionString = connectionString;
		this.namespace = namespace;
	}

	/**
	 * 创建默认的{@code ZkConnection}
	 *
	 * @return
	 */
	public ZkConnection createConnection() {
		CuratorFramework framework = CuratorFrameworkFactory.builder().connectString(connectionString)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).retryPolicy(retryPolicy)
				.canBeReadOnly(canBeReadOnly).namespace(namespace).build();

		framework.start();

		return new ZkConnectionImpl(framework);
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public ExponentialBackoffRetry getRetryPolicy() {
		return retryPolicy;
	}

	public void setRetryPolicy(ExponentialBackoffRetry retryPolicy) {
		this.retryPolicy = retryPolicy;
	}

	public boolean isCanBeReadOnly() {
		return canBeReadOnly;
	}

	public void setCanBeReadOnly(boolean canBeReadOnly) {
		this.canBeReadOnly = canBeReadOnly;
	}

	public String getConnectionString() {
		return connectionString;
	}

	public String getNamespace() {
		return namespace;
	}

}
