package com.apusic.skynet.curator.listener;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import com.google.common.collect.Lists;

public class SharedCounterListener {
	private static final int QTY = 5;
	private static final String PATH = "/examples/counter";
	private static String connectString = "localhost:2181";
	private static CuratorFramework client = null;

	static {
		client = CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(1000, 3));
		client.start();
	}

	@Test
	public void test() {
		final Random rand = new Random();
		MySharedCounterListener example = new MySharedCounterListener();

		try {

			/**
			 * SharedCount(CuratorFramework client, String path, int seedValue)
			 * 
			 * @param client
			 *            CuratorFramework
			 * @param path
			 *            String
			 * @param seedValue
			 *            int
			 */
			SharedCount baseCount = new SharedCount(client, PATH, 0);
			baseCount.addListener(example);
			baseCount.start();

			List<SharedCount> examples = Lists.newArrayList();

			// 线程池
			ExecutorService service = Executors.newFixedThreadPool(QTY);
			for (int i = 0; i < QTY; ++i) {
				final SharedCount count = new SharedCount(client, PATH, 0);
				examples.add(count);

				Callable<Void> task = new Callable<Void>() {
					@Override
					public Void call() throws Exception {
						count.start();
						Thread.sleep(rand.nextInt(10000));

						System.out.println("Increment: "
								+ count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
						return null;
					}
				};

				service.submit(task);
			}

			service.shutdown();
			service.awaitTermination(10, TimeUnit.MINUTES);

			for (int i = 0; i < QTY; ++i) {
				examples.get(i).close();
			}

			baseCount.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
