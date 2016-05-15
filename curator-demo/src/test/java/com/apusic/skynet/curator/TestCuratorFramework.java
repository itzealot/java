package com.apusic.skynet.curator;

import java.util.Collection;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class TestCuratorFramework extends JunitTestCase {

	@Test
	public void test() throws Exception {
		// 获取根节点即 namespace
		System.out.println("namespace : " + client.getNamespace());

		Stat stat = client.checkExists().forPath("/");
		System.out.println("/ " + (stat == null ? "not exist" : "exist"));

		stat = client.checkExists().forPath("/a");
		if (stat == null) {
			client.create().forPath("/a", "psth is /a".getBytes());
		}
		System.out.println("/a " + (stat == null ? "not exist" : "exist"));

		stat = client.checkExists().forPath("/" + ROOT + "/a");
		System.out.println("/" + ROOT + "/a " + (stat == null ? "not exist" : "exist"));
	}

	@Test
	public void mkdir() throws Exception {

		// 创建的路径 path必须是单路径
		String path = "/abc/abc";
		System.out.println(client.create().forPath(path, path.getBytes()));
		System.out.println(new String(client.getData().forPath(path)));

		client.delete().forPath(path);
	}

	@Test
	public void crud() throws Exception {
		String path = "/crud";

		client.create().forPath(path, "hello world".getBytes());
		byte[] bs = client.getData().forPath(path);
		System.out.println("新建的节点,data为: " + new String(bs));

		// 更新数据
		client.setData().forPath(path, "hello china".getBytes());

		// 由于是在background模式下获取的data，此时的 bs可能为null
		byte[] bs2 = client.getData().watched().inBackground().forPath(path);
		System.out.println("新修改的节点,data为: " + new String(bs2 != null ? bs2 : new byte[0]));

		client.delete().forPath(path);
		Stat stat = client.checkExists().forPath(path);

		// Stat 就是对 zonde 所有属性的一个映射， stat=null 表示节点不存在！
		System.out.println(stat);
	}

	@Test
	public void withMode() throws Exception {
		String path = "/withMode";

		byte[] bytes = null;
		// 创建
		client.create().withMode(CreateMode.EPHEMERAL).forPath(path, new String(path).getBytes());

		// 得到值
		bytes = client.getData().forPath(path);
		System.out.println(new String(bytes));

		// 得到子节点 List<String>
		List<String> ls = client.getChildren().forPath(path);
		System.out.println(ls == null ? "null" : ls.size());

		// 修改
		client.setData().forPath(path, bytes);

		// 删除
		client.delete().forPath(path);
	}

	@Test
	public void inBackgroundGetData() throws Exception {
		String path = "/inBackground";

		// 创建节点路径为PATH的数据节点
		client.create().forPath(path, "I love messi".getBytes());

		// 创建节点路径为 PATH 不带数据的节点
		// client.create().forPath(PATH);

		// 获取节点路径为 PATH 的数据
		byte[] bs = client.getData().forPath(path);
		System.out.println("新建的节点，data为:" + new String(bs));

		// 更新节点路径为 PATH 的数据
		client.setData().forPath(path, "I love football".getBytes());
		bs = client.getData().forPath(path);
		System.out.println("修改后的data为:" + new String(bs));

		// 由于是在 background 模式下获取的data，此时的bs可能为null
		byte[] bs2 = client.getData().watched().inBackground().forPath(path);
		System.out.println("修改后的data为:" + new String(bs2 != null ? bs2 : new byte[0]));

		// 删除路径为 PATH 的节点
		client.delete().forPath(path);

		// Stat 就是对 zonde 所有属性的一个映射， stat=null表示节点不存在！
		Stat stat = client.checkExists().forPath(path);
		System.out.println(stat == null);
	}

	/**
	 * 事务操作
	 * 
	 * create()增
	 * 
	 * delete(): 删
	 * 
	 * checkExists(): 判断是否存在
	 * 
	 * setData(): 改
	 * 
	 * getData(): 查
	 * 
	 * 所有这些方法都以forpath()
	 * 结尾，辅以watch(监听)，withMode（指定模式），和inBackground（后台运行）等方法来使用。
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void transaction() throws Exception {
		// 开启事务
		CuratorTransaction transaction = client.inTransaction();

		Collection<CuratorTransactionResult> results = transaction.create().forPath("/a/path", "some data".getBytes())
				.and().setData().forPath("/another/path", "other data".getBytes()).and().delete()
				.forPath("/yet/another/path").and().commit();

		for (CuratorTransactionResult result : results) {
			System.out.println(result.getForPath() + " - " + result.getType());
		}
	}

	@Test
	public void callback() throws Exception {
		final String path = "/callback";

		BackgroundCallback callback = new BackgroundCallback() {
			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				System.out.println("event data is : " + get(event.getData()));
				System.out.println("data is : " + get(client.getData().forPath(path)));
			}

		};

		// To create
		client.create().inBackground(callback).forPath(path, path.getBytes());

		System.out.println(get(client.getData().forPath(path)));
	}

	@Test
	public void listener() throws Exception {

		final String path = "/listener";

		client.delete().forPath(path);

		client.create().forPath(path, path.getBytes());

		client.setData().forPath(path, "aaa".getBytes());

		CuratorListener listener = new CuratorListener() {
			@Override
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {

				System.out.println("event data is : " + get(event.getData()));
				System.out.println("data is : " + get(client.getData().forPath(path)));
			}
		};

		client.getCuratorListenable().addListener(listener);
	}

	@Test
	public void watch() throws Exception {
		final String path = "/watch";

		if (client.checkExists().forPath(path) == null) {
			client.create().forPath(path, path.getBytes());
		} else {
			client.setData().forPath(path, "aaa".getBytes());
		}

		CuratorWatcher watcher = new CuratorWatcher() {
			@Override
			public void process(WatchedEvent event) throws Exception {
				System.out.println(new String(client.getData().forPath(path)));
			}
		};

		// 使用Watcher
		client.getData().usingWatcher(watcher).forPath(path);
	}

	@Test
	public void testCreateParents() throws Exception {
		String path = "/testCreateParents/t1/t1/t1";
		client.create().creatingParentsIfNeeded().forPath(path, path.getBytes());

		System.out.println("----------------------------------------------");
		System.out.println(get(client.getData().forPath(path)));
		System.out.println("----------------------------------------------");

		client.delete().deletingChildrenIfNeeded().forPath("/testCreateParents");

		System.out.println("----------------------------------------------");
		System.out.println(client.getChildren().forPath("/"));
		System.out.println("----------------------------------------------");
	}

	@Test
	public void testCreate() {
		String path = "/";
		try {
			String p = client.create().forPath(path, path.getBytes());

			System.out.println(p);

			client.getData().forPath(p);

			client.delete().forPath(path);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String get(byte[] data) {
		return new String(data);
	}
}
