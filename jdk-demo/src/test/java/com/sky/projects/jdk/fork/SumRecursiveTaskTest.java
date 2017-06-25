package com.sky.projects.jdk.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import com.sky.projects.jdk.thread.Threads;

/**
 * 1*1+2*2+3*3+...........100*100
 * 
 * ForkJoinTask类的几个重要 : <br>
 * fork() : 将任务放入队列并安排异步执行，一个任务应该只调用一次fork()函数，除非已经执行完毕并重新初始化。 <br>
 * tryUnfork() : 尝试把任务从队列中拿出单独处理，但不一定成功。 <br>
 * join() : 等待计算完成并返回计算结果。 <br>
 * isCompletedAbnormally() : 用于判断任务计算是否发生异常。
 * 
 * @author zealot
 *
 */
public class SumRecursiveTaskTest {

	public static void main(String[] args) {
		SumRecursiveTask task = new SumRecursiveTask(1);

		ForkJoinPool forkJoinPool = new ForkJoinPool();

		long start = System.currentTimeMillis();
		Future<Integer> result = forkJoinPool.submit(task);

		try {
			System.out.println("1*1+2*2+3*3+...........+100*100 = " + result.get());
			System.out.println("spends : " + (System.currentTimeMillis() - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		forkJoinPool.shutdown();

		start = System.currentTimeMillis();
		long sum = 0;
		for (int i = 1; i <= 1000; i++) {
			sum += i;
			Threads.sleep(100);
		}
		System.out.println("1*1+2*2+3*3+...........+100*100 = " + sum);
		System.out.println("spends : " + (System.currentTimeMillis() - start));
	}
}