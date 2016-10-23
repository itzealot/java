package com.sky.projects.pool.hive;

import java.sql.Connection;
import java.util.Properties;

import com.sky.projects.pool.ConnectionException;
import com.sky.projects.pool.ConnectionPool;
import com.sky.projects.pool.PoolBase;
import com.sky.projects.pool.PoolConfig;

/**
 * hive 连接池，用于获取连接，归还连接，销毁连接
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
public class HiveConnectionPool extends PoolBase<Connection> implements ConnectionPool<Connection> {

	public HiveConnectionPool() {
		this(HiveConfig.DEFAULT_HIVE_URL);
	}

	public HiveConnectionPool(final String hiveUrl) {
		this(new PoolConfig(), hiveUrl);
	}

	public HiveConnectionPool(final Properties properties) {
		this(new PoolConfig(), properties);
	}

	public HiveConnectionPool(final PoolConfig poolConfig, final String hiveUrl) {
		super(poolConfig, new HiveConnectionFactory(hiveUrl));
	}

	public HiveConnectionPool(final PoolConfig poolConfig, final Properties properties) {
		super(poolConfig, new HiveConnectionFactory(properties));
	}

	@Override
	public Connection getConnection() throws ConnectionException {
		return super.getResource();
	}

	@Override
	public void returnConnection(Connection conn) throws ConnectionException {
		super.returnResource(conn);
	}

	@Override
	public void invalidateConnection(Connection conn) throws ConnectionException {
		super.invalidateResource(conn);
	}

}
