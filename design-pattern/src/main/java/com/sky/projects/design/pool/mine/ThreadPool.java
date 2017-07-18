package com.sky.projects.design.pool.mine;

public interface ThreadPool {

	/**
	 * 提交任务
	 * 
	 * @param runnable
	 */
	void submit(Runnable runnable);

	/**
	 * 关闭线程池
	 */
	void shutdown();

	/**
	 * 线程池是否关闭
	 * 
	 * @return
	 */
	boolean isShutdown();

}
