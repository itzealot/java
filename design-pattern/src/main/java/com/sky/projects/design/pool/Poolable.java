package com.sky.projects.design.pool;

/**
 * 线程池接口
 * 
 * @author zealot
 */
public interface Poolable extends Runnable {

	/**
	 * 任务线程停止执行任务
	 */
	void shutdown();

	/**
	 * 任务线程是否关闭
	 * 
	 * @return
	 */
	boolean isShutdown();

	/**
	 * 向任务线程提交任务
	 * 
	 * @param runnabe
	 */
	void submit(Runnable runnable);

	/**
	 * 任务线程是否空闲
	 * 
	 * @return
	 */
	boolean isIdle();

	/**
	 * 运行线程
	 */
	void start();

}
