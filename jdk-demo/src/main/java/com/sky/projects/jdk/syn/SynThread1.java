package com.sky.projects.jdk.syn;

public class SynThread1 implements Runnable {
	private SynResource resource;

	public SynThread1(SynResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		while (true) {
			resource.add1();
		}
	}
}