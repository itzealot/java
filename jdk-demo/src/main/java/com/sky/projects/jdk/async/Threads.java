package com.sky.projects.jdk.async;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class Threads {

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO
			e.printStackTrace();
		}
	}

	public static <T> T get(Future<T> future) {
		try {
			return future.get();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}

		return null;
	}

	public static <T> T get(Future<T> future, long timeout, TimeUnit unit) {
		try {
			return future.get(timeout, unit);
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}

		return null;
	}
}
