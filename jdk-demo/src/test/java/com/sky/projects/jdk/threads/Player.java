package com.sky.projects.jdk.threads;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 4 * 100 接力赛运动员
 * 
 * @author zt
 *
 */
public class Player implements Runnable {

	// 运动员名称
	private String name;

	// 共享 CyclicBarrier 数据
	private CyclicBarrier barrier;

	// 下一棒
	private Player next;

	// 用时
	private int time = 0;

	// 第一棒
	private boolean run;

	public Player(String name, CyclicBarrier barrier, boolean run) {
		super();
		this.name = name;
		this.barrier = barrier;
		this.run = run;
	}

	@Override
	public void run() {
		int spendTime = 8 + new Random().nextInt(3);
		try {
			Thread.sleep(spendTime * 800);
			System.out.println(name + " 用时: " + spendTime);

			barrier.await();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
	}

	// The getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}

	public void setBarrier(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	public Player getNext() {
		return next;
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

}
