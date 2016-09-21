package com.sky.projects.pool.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.sky.projects.pool.ConnectionException;
import com.sky.projects.pool.ConnectionFactory;

/**
 * hive 连接工厂，用于创建连接
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
public class HiveConnectionFactory implements ConnectionFactory<Connection> {
	private String hiveUrl;

	private void loadDriver() {
		try {
			Class.forName(HiveConfig.DEFAULT_DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO
		}

	}

	public HiveConnectionFactory(String hiveUrl) {
		super();
		this.hiveUrl = hiveUrl;
		this.loadDriver();
	}

	public HiveConnectionFactory(final Properties properties) {
		this.hiveUrl = properties.getProperty(HiveConfig.HIVE_URL_PROPERTY);
		if (hiveUrl == null)
			throw new ConnectionException("[" + HiveConfig.HIVE_URL_PROPERTY + "] is required !");

		this.loadDriver();
	}

	@Override
	public PooledObject<Connection> makeObject() throws Exception {
		return new DefaultPooledObject<Connection>(this.createConnection());
	}

	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception {
		Connection connection = p.getObject();
		if (connection != null)
			connection.close();
	}

	@Override
	public boolean validateObject(PooledObject<Connection> p) {
		Connection connection = p.getObject();
		try {
			if (connection != null)
				return ((!connection.isClosed()) && (connection.isValid(1)));
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public void activateObject(PooledObject<Connection> p) throws Exception {
		// TODO
	}

	@Override
	public void passivateObject(PooledObject<Connection> p) throws Exception {
		// TODO
	}

	@Override
	public Connection createConnection() throws Exception {
		return DriverManager.getConnection(hiveUrl);
	}

}
