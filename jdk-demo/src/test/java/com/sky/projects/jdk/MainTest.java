package com.sky.projects.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * 1*1+2*2+3*3+...........100*100
 * 
 * @author zt
 *
 */
public class MainTest {

	public static void main(String[] args) {
		MyTask mt = new MyTask(1);

		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.currentTimeMillis();
		long end = 0L;
		Future<Integer> result = forkJoinPool.submit(mt);

		try {
			System.out.println("1*1+2*2+3*3+...........+100*100 = " + result.get());

			end = System.currentTimeMillis();

			System.out.println("spends : " + (end - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		forkJoinPool.shutdown();

		start = System.currentTimeMillis();
		long sum = 0;
		try {
			for (int i = 1; i <= 1000; i++) {
				sum += i;

				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO
			e.printStackTrace();
		}

		System.out.println("1*1+2*2+3*3+...........+100*100 = " + sum);

		end = System.currentTimeMillis();

		System.out.println("spends : " + (end - start));
	}
}