package com.sky.projects.design.common;

import java.util.concurrent.Future;

public final class Threads {

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static String get(Future<String> future) {
		try {
			return future.get();
		} catch (Exception e) {
			return null;
		}
	}

	public static void wait(Object obj) {
		try {
			obj.wait();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private Threads() {
	}
}