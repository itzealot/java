package com.sky.projects.apache.common.io.monitor;

import org.apache.log4j.helpers.FileWatchdog;

public class FileListener extends FileWatchdog {

	protected FileListener(String filename) {
		super(filename);
	}

	@Override
	protected void doOnChange() {
		System.out.println("file changed ...........");
	}

}
