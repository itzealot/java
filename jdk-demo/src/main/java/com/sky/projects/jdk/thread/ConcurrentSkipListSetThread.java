package com.sky.projects.jdk.thread;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

import com.projects.sky.util.base.Threads;

/**
 * 1. ConcurrentSkipListSet<T> 提供的功能类似于TreeSet，线程安全且有序的 set
 * 
 * 2. 基于"跳跃列表（skip list)"实现的，只要多个线程没有同时修改集合的同一个部分，则在正常读、写集合的操作中不会出现竞争现象。
 * 
 * 3. 假设业务场景： <br>
 * 1). 当用户第一次查询时会把所以的数据都从数据库当中查询出来，放入cache当中。<br>
 * 2). 如果有增加时，会把这个新用用户放入并行集合类ConcurrentSkipListSet 当中。 <br>
 * 3). 删除时，数据库删除后会把这个用户信息从 ConcurrentSkipListSet 中删除。<br>
 * 4). 修改时，会从ConcurrentSkipListSet 中根据ID找到这个数据。然后删除。然后ADD进去。<br>
 * 
 * @author zt
 */
public class ConcurrentSkipListSetThread implements Runnable {
	// 共享
	private ConcurrentSkipListSet<Integer> set;

	public ConcurrentSkipListSetThread(ConcurrentSkipListSet<Integer> set) {
		super();
		this.set = set;
	}

	@Override
	public void run() {
		while (true) {
			set.add(new Random().nextInt(100));// 产生数字并添加
			display();
		}
	}

	public void display() {
		System.out.println(set);
		Threads.sleep(1000);
	}
}
