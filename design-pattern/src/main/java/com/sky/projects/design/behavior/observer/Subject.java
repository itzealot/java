package com.sky.projects.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部维护一个观察者列表，当状态变更时通知对应的观察者
 * 
 * @author zealot
 */
public class Subject {

	// 观测者列表
	private List<StateObserver> observers = new ArrayList<>();
	private int state;

	/**
	 * 向观测者列表添加观测者
	 * 
	 * @param observer
	 */
	public void add(StateObserver observer) {
		observers.add(observer);
	}

	/**
	 * 更新观察者列表中所有的观察者
	 */
	private void notifyObservers(int state) {
		for (StateObserver observer : observers) {
			observer.notify(state);
		}
	}

	public int getState() {
		return state;
	}

	/**
	 * 状态变更时通知观察者
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
		notifyObservers(state);
	}
}