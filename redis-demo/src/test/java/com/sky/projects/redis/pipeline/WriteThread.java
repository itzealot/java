package com.sky.projects.redis.pipeline;

import java.util.ArrayList;
import java.util.List;

public class WriteThread implements Runnable {

	private String file;
	private List<Result> ress;

	public WriteThread(String file, List<Result> ress) {
		super();
		this.file = file;
		this.ress = ress;
	}

	@Override
	public void run() {
//		Files.appendWithBufferedWriter(file, exchange());
	}

	private List<String> exchange() {
		List<String> lists = new ArrayList<>();

		for (Result r : ress) {
			lists.add(r.toString());
		}

		return lists;
	}
}
