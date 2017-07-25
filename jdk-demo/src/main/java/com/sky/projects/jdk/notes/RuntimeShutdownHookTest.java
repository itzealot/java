package com.sky.projects.jdk.notes;

import java.util.Random;

import com.sky.projects.jdk.thread.Threads;

/**
 * 这个方法的意思就是在jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法 addShutdownHook
 * 添加的钩子，当系统执行完这些钩子后，jvm才会关闭。 所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
 * 
 * @author zealot
 */
public class RuntimeShutdownHookTest {

	public static void main(String[] args) {
		// addShutdownHook方法适用于：当jvm关闭时进行清理工作
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("shutdownHook thread finish...");
		}));

		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				Random random = new Random();
				Threads.sleep(1000 + random.nextInt(2000));
				System.out.println("thread:" + Thread.currentThread().getName() + " finish...");
			}).start();
		}
	}
}