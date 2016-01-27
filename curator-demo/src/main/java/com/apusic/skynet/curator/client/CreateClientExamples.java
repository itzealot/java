package com.apusic.skynet.curator.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CreateClientExamples {
	public static CuratorFramework createSimple(String connectionString) {

		/**
		 * ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries)
		 * 
		 * baseSleepTimeMs : 连接次数间隔时间
		 * 
		 * maxRetries : 最多尝试连接次数
		 * 
		 * these are reasonable arguments for the ExponentialBackoffRetry. The
		 * first retry will wait 1 second, the second will wait up to 2 seconds
		 * the third will wait up to 4 seconds.
		 */
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

		/**
		 * The simplest way to get a CuratorFramework instance. This will use
		 * default values. The only required arguments are the connection string
		 * and the retry policy
		 * 
		 */
		return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
	}

	/**
	 * To create CuratorFramework
	 * 
	 * @param connectionString
	 * @param retryPolicy
	 * @param connectionTimeoutMs
	 * @param sessionTimeoutMs
	 * @return
	 */
	public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,
			int connectionTimeoutMs, int sessionTimeoutMs) {
		/**
		 * using the CuratorFrameworkFactory.builder() gives fine grained
		 * control over creation options. See the CuratorFrameworkFactory.
		 * Builder javadoc details
		 */
		return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs)
				// etc. etc.
				.build();
	}
}