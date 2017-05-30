package com.sky.projects.jdk.syn;

public class SynStaticThread3 implements Runnable {
	@Override
	public void run() {
		while (true) {
			SynStaticResource.add3();
		}
	}
}