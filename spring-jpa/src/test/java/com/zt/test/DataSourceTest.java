package com.zt.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {
	private ApplicationContext applicationContext = null;
	private DataSource dataSource = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testDataSource() throws SQLException {
		dataSource = applicationContext.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
}
