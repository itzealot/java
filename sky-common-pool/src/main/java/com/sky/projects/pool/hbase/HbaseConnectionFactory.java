/*
 * Copyright 2015-2016 Dark Phoenixs (Open-Source Organization).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sky.projects.pool.hbase;

import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;

import com.sky.projects.pool.ConnectionException;
import com.sky.projects.pool.ConnectionFactory;

/**
 * Hbase连接工厂
 * 
 * @author zealot
 *
 */
class HbaseConnectionFactory implements ConnectionFactory<HConnection> {

	/** serialVersionUID */
	private static final long serialVersionUID = 4024923894283696465L;

	/** hadoopConfiguration */
	private final Configuration hadoopConfiguration;

	public HbaseConnectionFactory(final Configuration hadoopConfiguration) {

		this.hadoopConfiguration = hadoopConfiguration;
	}

	/**
	 *
	 * @param host
	 *            zookeeper地址
	 * @param port
	 *            zookeeper端口
	 * @param master
	 *            hbase主机
	 * @param rootdir
	 *            hdfs数据目录
	 */
	public HbaseConnectionFactory(final String host, final String port, final String master, final String rootdir) {
		this.hadoopConfiguration = new Configuration();

		if (host == null)
			throw new ConnectionException("[" + HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY + "] is required !");
		this.hadoopConfiguration.set(HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY, host);

		if (port == null)
			throw new ConnectionException("[" + HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY + "] is required !");
		this.hadoopConfiguration.set(HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY, port);

		if (master != null)
			this.hadoopConfiguration.set(HbaseConfig.MASTER_PROPERTY, master);

		if (rootdir != null)
			this.hadoopConfiguration.set(HbaseConfig.ROOTDIR_PROPERTY, rootdir);
	}

	/**
	 * @since 1.2.1
	 * @param properties
	 *            参数配置
	 */
	public HbaseConnectionFactory(final Properties properties) {
		this.hadoopConfiguration = new Configuration();

		for (Entry<Object, Object> entry : properties.entrySet()) {
			this.hadoopConfiguration.set((String) entry.getKey(), (String) entry.getValue());
		}
	}

	@Override
	public PooledObject<HConnection> makeObject() throws Exception {
		return new DefaultPooledObject<HConnection>(this.createConnection());
	}

	@Override
	public void destroyObject(PooledObject<HConnection> p) throws Exception {

		HConnection connection = p.getObject();

		if (connection != null)

			connection.close();
	}

	@Override
	public boolean validateObject(PooledObject<HConnection> p) {

		HConnection connection = p.getObject();

		if (connection != null)

			return ((!connection.isAborted()) && (!connection.isClosed()));

		return false;
	}

	@Override
	public void activateObject(PooledObject<HConnection> p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void passivateObject(PooledObject<HConnection> p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public HConnection createConnection() throws Exception {
		HConnection connection = HConnectionManager.createConnection(hadoopConfiguration);
		return connection;
	}

}
