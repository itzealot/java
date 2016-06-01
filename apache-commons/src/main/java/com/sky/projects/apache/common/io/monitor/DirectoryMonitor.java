package com.sky.projects.apache.common.io.monitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class DirectoryMonitor {

	FileAlterationMonitor monitor = null;

	public DirectoryMonitor(long interval) throws Exception {
		monitor = new FileAlterationMonitor(interval);
	}

	public void monitor(String path, FileAlterationListener listener) {
		FileAlterationObserver observer = new FileAlterationObserver(new File(path));

		observer.addListener(listener);

		monitor.addObserver(observer);
	}

	public void stop() throws Exception {
		monitor.stop();
	}

	public void start() throws Exception {
		monitor.start();
	}

}