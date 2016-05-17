package com.sky.projects.jdk.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 保存所有的 Future
 * 
 * @author zt
 *
 * @param <T>
 */
public class FutureContext<T> {
	private List<Future<T>> futures = new ArrayList<Future<T>>();

	// 线程池
	private ExecutorService service;

	public void addFuture(Future<T> future) {
		this.futures.add(future);
	}

	public List<Future<T>> getFutureList() {
		return this.futures;
	}

	public void setService(ExecutorService service) {
		this.service = service;
	}

	public ExecutorService getService() {
		return service;
	}
}