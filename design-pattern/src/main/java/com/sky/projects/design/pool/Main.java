package com.sky.projects.design.pool;

public class Main {
	public static void main(String[] args) {
		ExecuterPool pool = ExecuterPool.getInstance();

		for (int i = 0; i < 20; i++) {
			pool.submit(new TaskThread(String.valueOf(i)));
		}

		// pool.shutdown();
	}
}
