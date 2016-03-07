package com.zt.test.async;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 异步执行控制器
 * 
 * @author zt
 *
 */
public class AsyncController {

	// 线程池
	private ExecutorService service = Executors.newFixedThreadPool(100);

	// 保存异步计算的Future
	private FutureContext<String> context;

	public AsyncController() {
		this.context = new FutureContext<String>();
	}

	/**
	 * 异步计算
	 */
	public void startAsyncCompution() {
		final Random random = new Random();

		// 开启100个异步计算，每个异步计算线程随机sleep几秒来模拟计算耗时。
		for (int i = 0; i < 100; i++) {
			Future<String> future = this.service.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					int randomInt = random.nextInt(10);

					Thread.sleep(randomInt * 1000);

					// 返回随机数值
					return "" + randomInt;
				}
			});

			// 每个异步计算的结果存放在context中
			this.context.addFuture(future);
		}
	}

	public FutureContext<String> getFutureContext() {
		return this.context;
	}

	public ExecutorService getService() {
		return service;
	}
}