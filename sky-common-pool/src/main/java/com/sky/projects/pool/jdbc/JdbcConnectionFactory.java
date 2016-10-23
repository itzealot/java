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
package com.sky.projects.pool.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.sky.projects.pool.ConnectionException;
import com.sky.projects.pool.ConnectionFactory;

/**
 * JDBC连接工厂
 * 
 * @author zealot
 *
 */
@SuppressWarnings("serial")
class JdbcConnectionFactory implements ConnectionFactory<Connection> {

	/** driverClass */
	private final String driverClass;

	/** jdbcUrl */
	private final String jdbcUrl;

	/** username */
	private final String username;

	/** password */
	private final String password;

	private void loadDriver() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public JdbcConnectionFactory(final Properties properties) {
		this.driverClass = properties.getProperty(JdbcConfig.DRIVER_CLASS_PROPERTY);
		if (driverClass == null)
			throw new ConnectionException("[" + JdbcConfig.DRIVER_CLASS_PROPERTY + "] is required !");

		this.jdbcUrl = properties.getProperty(JdbcConfig.JDBC_URL_PROPERTY);
		if (jdbcUrl == null)
			throw new ConnectionException("[" + JdbcConfig.JDBC_URL_PROPERTY + "] is required !");

		this.username = properties.getProperty(JdbcConfig.JDBC_USERNAME_PROPERTY);
		if (username == null)
			throw new ConnectionException("[" + JdbcConfig.JDBC_USERNAME_PROPERTY + "] is required !");

		this.password = properties.getProperty(JdbcConfig.JDBC_PASSWORD_PROPERTY);
		if (password == null)
			throw new ConnectionException("[" + JdbcConfig.JDBC_PASSWORD_PROPERTY + "] is required !");

		this.loadDriver();
	}

	/**
	 * 
	 * @param driverClass
	 *            驱动类
	 * @param jdbcUrl
	 *            数据库URL
	 * @param username
	 *            数据库用户名
	 * @param password
	 *            数据密码
	 */
	public JdbcConnectionFactory(final String driverClass, final String jdbcUrl, final String username,
			final String password) {
		this.driverClass = driverClass;
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
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
		if (connection != null)
			try {
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
		return DriverManager.getConnection(jdbcUrl, username, password);
	}

}
