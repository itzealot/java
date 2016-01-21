package com.apusic.skynet.zookeeper;

import com.apusic.skynet.zookeeper.ZkConnection;
import com.apusic.skynet.zookeeper.ZkConnectionFactory;

public class BaseJunitTest {
	private String connectionString = "localhost:2181";
	protected String namespace = "com.apusic.test";
	protected ZkConnection connection = ZkConnectionFactory.create(connectionString, namespace);

}
