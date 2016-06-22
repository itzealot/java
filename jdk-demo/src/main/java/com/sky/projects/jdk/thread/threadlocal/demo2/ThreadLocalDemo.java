package com.sky.projects.jdk.thread.threadlocal.demo2;

import java.util.Random;

import com.sky.projects.jdk.thread.threadlocal.A;
import com.sky.projects.jdk.thread.threadlocal.B;

public class ThreadLocalDemo {
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " has put data : " + data);

					local.set(data); // 向 ThreadLocal 放数据

					new A<Integer>(local).get();
					new B<Integer>(local).get();
				}
			}).start();
		}
	}
}
