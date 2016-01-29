package com.zt.test.design.observer;

import java.util.Vector;

/**
 * 具体主题实现了主题接口中的方法，如新增观察者、删除观察者和通知观察者.其内部维护一个观察者列表。
 * 
 * @author a
 *
 */
public class ConcreteSubject implements ISubject {

	// 存储所有的观察者
	private Vector<IObserver> observers = new Vector<IObserver>();

	/**
	 * 添加观察者
	 */
	public void attach(IObserver observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	/**
	 * 删除观察者
	 */
	public void detach(IObserver observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	/**
	 * 通知所有观察者
	 */
	public void info() {
		// TODO Auto-generated method stub
		Event event = new Event();

		int length = observers.size();

		// 通知观察者
		for (int i = 0; i < length; i++) {
			observers.get(i).update(event);
		}
	}

}
