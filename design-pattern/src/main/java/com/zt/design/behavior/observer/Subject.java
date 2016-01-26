package com.zt.design.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * subject 对象带有绑定观察者到 Client 对象和从 Client 对象解绑观察者的方法
 * 
 * @author zengtao
 *
 */
public class Subject {

	// List<Observer> observers
	private List<Observer> observers = new ArrayList<Observer>();
	private int state;

	public int getState() {
		return state;
	}

	/**
	 * 设置状态并更新所有观察者
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;

		// update method
		notifyAllObservers();
	}

	/**
	 * To add Observer observer into List<Observer> observers
	 * 
	 * @param observer
	 */
	public void attach(Observer observer) {
		observers.add(observer);
	}

	/**
	 * To update List<Observer> observers all Observer
	 */
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}