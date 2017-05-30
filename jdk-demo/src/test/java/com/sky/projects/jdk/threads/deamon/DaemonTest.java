package com.sky.projects.jdk.threads.deamon;

import com.sky.projects.jdk.thread.Threads;
import com.sky.projects.jdk.thread.deamon.DeamonThread;
import com.sky.projects.jdk.thread.deamon.NoneDeamonThread;

/**
 * Java语言自己可以创建两种进程“用户线程”和“守护线程”
 * 
 * --1). 用户线程：就是我们平时创建的普通线程.
 * 
 * --2). 守护线程：主要是用来服务用户线程.
 * 
 * 当线程只剩下守护线程的时候，JVM就会退出.但是如果还有其他的任意一个用户线程还在，JVM就不会退出
 * 
 * @author zt
 *
 */
public class DaemonTest {

	public static void main(String[] args) {
		System.out.println("==>Main Thread running...");

		Thread deamonThread = new Thread(new DeamonThread());

		// 守护线程,Set deamon true
		deamonThread.setDaemon(true);
		deamonThread.start();

		// 非守护线程
		new Thread(new NoneDeamonThread()).start();

		// Main Thread sleep 5s
		Threads.sleep(5000);

		// Main Thread ending
		System.out.println("==> Main Thread ending...");
	}

}