package com.sky.projects.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * subject 对象带有绑定观察者到 Client 对象和从 Client 对象解绑观察者的方法
 * 
 * @author zealot
 *
 */
public class Subject {

	// 观测者列表
	private List<Observer> observers = new ArrayList<>();

	private int state;

	/**
	 * 向观测者列表添加观测者
	 * 
	 * @param observer
	 */
	public void attach(Observer observer) {
		observers.add(observer);
	}

	/**
	 * 更新观察者列表中所有的观察者
	 */
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public int getState() {
		return state;
	}

	/**
	 * 设置新状态并更新所有观察者信息
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}
}