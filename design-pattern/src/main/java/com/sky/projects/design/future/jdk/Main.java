package com.sky.projects.design.future.jdk;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import com.sky.projects.design.common.Threads;

/**
 * 使用 JDK 内置的 Future 模式及 ExecutorService 框架运行任务
 * 
 * @author zealot
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<String> callable = new RealData("1");

		// 根据 Callable<V> 实现类构造 FutureTask<V>
		// FutureTask<V> 作为单独线程运行
		FutureTask<String> future = new FutureTask<>(callable);

		ExecutorService service = Executors.newFixedThreadPool(1);

		// 开启线程运行，即执行 call 方法
		service.submit(future);

		System.out.println("start do other things.....");
		Threads.sleep(10000);
		System.out.println("finish do other things.....");

		// handle the result
		System.out.println("result : " + future.get());

		service.shutdown();
	}
}
