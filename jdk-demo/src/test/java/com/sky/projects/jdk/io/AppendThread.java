package com.sky.projects.jdk.io;

import java.util.List;

public class AppendThread implements Runnable {

	private AppendStrategy startegy;
	protected String file;
	protected List<String> contents;

	public AppendThread(String file, List<String> contents, AppendStrategy startegy) {
		super();
		this.file = file;
		this.contents = contents;
		this.startegy = startegy;
	}

	@Override
	public void run() {
		startegy.write(file, contents);
	}

}
