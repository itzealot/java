package com.sky.projects.jdk.observer;

import java.util.Vector;

/**
 * 具体主题实现了主题接口中的方法，如新增观察者、删除观察者和通知观察者.其内部维护一个观察者列表。
 * 
 * @author zealot
 *
 */
public class ConcreteSubject implements Subjectable {

	// 存储所有的观察者
	private Vector<Observerable> observers = new Vector<Observerable>();

	@Override
	public void attach(Observerable observer) {
		observers.add(observer);
	}

	@Override
	public void detach(Observerable observer) {
		observers.remove(observer);
	}

	@Override
	public void info() {
		Event event = new Event();

		int length = observers.size();

		// 通知观察者
		for (int i = 0; i < length; i++) {
			observers.get(i).update(event);
		}
	}

}
