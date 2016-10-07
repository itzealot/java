package com.sky.projects.design.pool;

/**
 * 执行任务的线程封装类
 * 
 * @author zealot
 */
public class Executer extends Thread implements Poolable {

	private ExecuterPool pool; // 线程池
	private volatile Runnable target; // 任务
	private volatile boolean isShutdown = false; // 是否关闭标记
	private boolean isIdle = true; // 线程是否闲置标记

	public Executer(ExecuterPool pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		while (!isShutdown) {
			if (target != null) {
				isIdle = false; // 修改闲置标记
				target.run(); // 运行任务
				target = null;
				isIdle = true; // 任务完成，修改闲置标记，不关闭线程，把其放入到线程池中
				pool.repool(this);
			}
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public synchronized void shutdown() {
		this.isShutdown = true;
	}

	@Override
	public boolean isShutdown() {
		return isShutdown;
	}

	@Override
	public synchronized void submit(Runnable runnable) {
		this.target = runnable;
	}

	@Override
	public boolean isIdle() {
		return isIdle;
	}

}
