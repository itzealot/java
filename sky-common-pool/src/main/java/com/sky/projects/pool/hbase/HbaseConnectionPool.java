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

import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HConnection;

import com.sky.projects.pool.ConnectionPool;
import com.sky.projects.pool.PoolBase;
import com.sky.projects.pool.PoolConfig;

/**
 * Hbase连接池
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
public class HbaseConnectionPool extends PoolBase<HConnection> implements ConnectionPool<HConnection> {

	public HbaseConnectionPool() {
		this(HbaseConfig.DEFAULT_HOST, HbaseConfig.DEFAULT_PORT);
	}

	public HbaseConnectionPool(final String host, final String port) {
		this(new PoolConfig(), host, port, HbaseConfig.DEFAULT_MASTER, HbaseConfig.DEFAULT_ROOTDIR);
	}

	/**
	 *
	 * @param host
	 *            地址
	 * @param port
	 *            端口
	 * @param master
	 *            hbase主机
	 * @param rootdir
	 *            hdfs目录
	 */
	public HbaseConnectionPool(final String host, final String port, final String master, final String rootdir) {
		this(new PoolConfig(), host, port, master, rootdir);
	}

	public HbaseConnectionPool(final Configuration hadoopConfiguration) {
		this(new PoolConfig(), hadoopConfiguration);
	}

	/**
	 *
	 * @param poolConfig
	 *            池配置
	 * @param host
	 *            地址
	 * @param port
	 *            端口
	 */
	public HbaseConnectionPool(final PoolConfig poolConfig, final String host, final String port) {
		this(poolConfig, host, port, HbaseConfig.DEFAULT_MASTER, HbaseConfig.DEFAULT_ROOTDIR);
	}

	/**
	 *
	 * @param poolConfig
	 *            池配置
	 * @param hadoopConfiguration
	 *            hbase配置
	 */
	public HbaseConnectionPool(final PoolConfig poolConfig, final Configuration hadoopConfiguration) {
		super(poolConfig, new HbaseConnectionFactory(hadoopConfiguration));
	}

	/**
	 *
	 * @param poolConfig
	 *            池配置
	 * @param host
	 *            地址
	 * @param port
	 *            端口
	 * @param master
	 *            hbase主机
	 * @param rootdir
	 *            hdfs目录
	 */
	public HbaseConnectionPool(final PoolConfig poolConfig, final String host, final String port, final String master,
			final String rootdir) {
		super(poolConfig, new HbaseConnectionFactory(host, port, master, rootdir));
	}

	/**
	 * @param poolConfig
	 *            池配置
	 * @param properties
	 *            参数配置
	 */
	public HbaseConnectionPool(final PoolConfig poolConfig, final Properties properties) {
		super(poolConfig, new HbaseConnectionFactory(properties));
	}

	@Override
	public HConnection getConnection() {
		return super.getResource();
	}

	@Override
	public void returnConnection(HConnection conn) {
		super.returnResource(conn);
	}

	@Override
	public void invalidateConnection(HConnection conn) {
		super.invalidateResource(conn);
	}

}
