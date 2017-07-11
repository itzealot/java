package com.sky.projects.jdk.thread;

public class InterruptThreadTest {

	public static void main(String args[]) throws Exception {
		Thread thread = new Thread(new InterruptThread());
		thread.start();

		Thread.sleep(3000);
		System.out.println("Asking thread to stop...");
		thread.interrupt(); // 主线程发出中断请求，中断某个线程
		
		Thread.sleep(3000);
		System.out.println("Asking thread to stop...");
		thread.interrupt(); // 主线程发出中断请求，中断某个线程
	}

	static class InterruptThread implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("running...");

				try {
					/*
					 * 如果线程阻塞，将不会去检查中断信号量stop变量，所 以thread.interrupt()
					 * 会使阻塞线程从阻塞的地方抛出异常，让阻塞线程从阻塞状态逃离出来，并进行异常块进行相应的处理
					 */
					Thread.sleep(1000); // 线程阻塞，如果线程收到中断操作信号将抛出异常
				} catch (InterruptedException e) {
					System.out.println("Thread interrupted...");

					/*
					 * 如果线程在调用 Object.wait()方法，或者该类的 join() 、sleep()方法
					 * 过程中受阻，则其中断状态将被清除
					 */
					System.out.println(Thread.currentThread().isInterrupted()); // false

					// 中不中断由自己决定，如果需要真中断线程，则需要重新设置中断位，如果不需要，则不用调用
//					Thread.currentThread().interrupt(); // 响应中断请求
//					System.out.println(Thread.currentThread().isInterrupted()); // true
				}
			}

			System.out.println("Thread exiting under request...");
		}
	}
}