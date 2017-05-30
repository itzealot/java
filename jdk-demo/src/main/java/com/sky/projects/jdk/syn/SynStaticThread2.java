package com.sky.projects.jdk.syn;
public class SynStaticThread2 implements Runnable {
		@Override
		public void run() {
			while (true) {
				SynStaticResource.add2();
			}
		}
	}