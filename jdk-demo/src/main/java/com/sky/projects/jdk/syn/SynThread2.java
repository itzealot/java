package com.sky.projects.jdk.syn;

public class SynThread2 implements Runnable {
	private SynResource resource;

	public SynThread2(SynResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		while (true) {
			resource.add2();
		}
	}
}