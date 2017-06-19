package com.sky.projects.jdk.thread;

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
			Thread.currentThread().interrupt();
			return null;
		}
	}

	private Threads() {
	}
}