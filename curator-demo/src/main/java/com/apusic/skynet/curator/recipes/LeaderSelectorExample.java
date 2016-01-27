package com.apusic.skynet.curator.recipes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

import com.apusic.skynet.curator.ClientFactory;
import com.google.common.collect.Lists;

/**
 * leader 选举
 * 
 * @author shencl
 */
public class LeaderSelectorExample {

	public static void main(String[] args) {

		List<CuratorFramework> clients = Lists.newArrayList();
		List<LeaderSelectorClient> examples = Lists.newArrayList();

		try {
			for (int i = 0; i < 10; i++) {
				CuratorFramework client = ClientFactory.newClient();

				// 创建自定义选举 Zookeeper 客户端
				LeaderSelectorClient example = new LeaderSelectorClient(client, "Client #" + i);
				clients.add(client);
				examples.add(example);

				client.start();
				example.start();
			}

			System.out.println("----------先观察一会选举的结果-----------");
			Thread.sleep(10000);

			System.out.println("----------关闭前5个客户端，再观察选举的结果-----------");
			for (int i = 0; i < 5; i++) {
				clients.get(i).close();
			}

			// 这里有个小技巧，让main程序一直监听控制台输入，异步的代码就可以一直在执行。不同于while(ture)的是，按回车或 esc
			// 可退出
			new BufferedReader(new InputStreamReader(System.in)).readLine();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// To close
			for (LeaderSelectorClient exampleClient : examples) {
				CloseableUtils.closeQuietly(exampleClient);
			}

			// To close
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}
		}
	}
}