package com.sky.projects.jdk.async;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 输出结果线程
 * 
 * @author zt
 *
 */
public class OutputResult implements Runnable {

	// 持有所有的 Future
	private FutureContext<String> context;

	@Override
	public void run() {
		System.out.println("start to output result:");

		// 获取所有的 Future
		List<Future<String>> list = this.context.getFutureList();

		// 输出所有的 Future 结果
		for (Future<String> future : list) {
			this.outputResultFromFuture(future);
		}

		System.out.println("finish to output result.");

		// 关闭线程池
		context.getService().shutdown();
	}

	/**
	 * 根据 Future 输出计算结果
	 * 
	 * @param future
	 */
	private void outputResultFromFuture(Future<String> future) {
		// 持续监听异步线程
		while (true) {
			// future.isDone() : 即任务完成，返回 true；否则返回 false
			// future.isCancelled() : 返回 true，则任务已经取消；否则返回 false
			if (future.isDone() && !future.isCancelled()) {
				System.out.println("Future: " + future + ", Result: " + Threads.get(future));

				// 任务执行完成，退出
				break;
			} else {
				Threads.sleep(1000);
			}
		}
	}

	public void setFutureContext(FutureContext<String> context) {
		this.context = context;
	}
}