package com.sky.projects.pool;

import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.sky.projects.pool.hbase.HbaseConnectionPool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	public void testPool() throws IOException {
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);

		/* properties */
		Properties props = new Properties();
		props.setProperty("hbase.zookeeper.quorum", "host1,host2,host3");
		props.setProperty("hbase.zookeeper.property.clientPort", "2181");
		props.setProperty("hbase.master", "host1:60000");
		props.setProperty("hbase.rootdir", "hdfs://host1:9000/hbase");

		/* connection pool */
		HbaseConnectionPool pool = new HbaseConnectionPool(config, props);
		HTableInterface table = null;

		HConnection conn = pool.getConnection();
		table = conn.getTable(TableName.valueOf("relation"));

		Get get = new Get(Bytes.toBytes("rowKey"));
		Result r = table.get(get);
		for (Cell cell : r.rawCells()) {
			System.out.println("Rowkey : " + Bytes.toString(r.getRow()) + " Familiy:Quilifier : "
					+ Bytes.toString(CellUtil.cloneQualifier(cell)) + " Value : "
					+ Bytes.toString(CellUtil.cloneValue(cell)));
		}
		table.close();
		System.out.println(table);
		pool.returnConnection(conn);

		pool.close();
	}
}
