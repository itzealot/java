package com.sky.projects.jdk.observer;

import java.util.Vector;

/**
 * 具体主题实现了主题接口中的方法，如新增观察者、删除观察者和通知观察者.其内部维护一个观察者列表。
 * 
 * ThreadSafe
 * 
 * @author zealot
 */
public class ConcreteSubject implements Subjectable {

	// 存储所有的观察者
	private Vector<Observerable> observers = new Vector<Observerable>();

	@Override
	public void attach(Observerable observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}

	@Override
	public void detach(Observerable observer) {
		synchronized (observers) {
			observers.remove(observer);
		}
	}

	@Override
	public void info() {
		Event event = new Event();

		// 此处加锁是防止在通知的时候出现数组越界问题，如刚添加观察者，又被删除，结果length还是之前的
		synchronized (observers) {
			for (int i = 0, length = observers.size(); i < length; i++) {
				observers.get(i).update(event);
			}
		}
	}

}
