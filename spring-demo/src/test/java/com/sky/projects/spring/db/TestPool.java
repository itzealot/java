package com.sky.projects.spring.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPool {

	private ApplicationContext applicationContext = null;

	@Test
	public void testConnectionXml() {
		connection("database-properties.xml");
	}

	@Test
	public void testConnection() {
		connection("beans-properties.xml");
	}

	public void connection(String conf) {
		applicationContext = new ClassPathXmlApplicationContext(conf);

		DataSource dataSource = applicationContext.getBean(DataSource.class);
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(connection);
	}
}
