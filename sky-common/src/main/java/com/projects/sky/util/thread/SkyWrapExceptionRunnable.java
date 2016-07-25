package com.projects.sky.util.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 保证不会有Exception抛出到线程池的Runnable，防止用户没有捕捉异常导致中断了线程池中的线程。
 * 
 * @author zealot
 */
public class SkyWrapExceptionRunnable implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(SkyWrapExceptionRunnable.class);

	private Runnable runnable;

	public SkyWrapExceptionRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

	public void run() {
		try {
			runnable.run();
		} catch (Throwable e) {
			/*
			 * catch any exception, because the scheduled thread will break if
			 * the exception thrown outside.
			 */
			LOG.error("error execute the Runnable.", e);
		}
	}
}