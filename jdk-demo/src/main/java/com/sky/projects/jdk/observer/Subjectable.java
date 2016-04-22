package com.sky.projects.jdk.observer;

/**
 * 1. 观察者模式是非常常用的设计模式，在软件系统中，当一个对象的行为依赖于另一个对象的状态时，观察者模式就想当有用。<br />
 * 2. 若不使用观察者模式提供的通用结构，需要实现类似功能，则只能在另一个线程中不断减退对象所依赖的状态。<br />
 * 3. 主题接口 ： 指被观察的对象。于其状态发生改变或者某事件发生时，将这个变化通知观察者。其维护观察者所需要依赖的状态。<br />
 * 
 * @author zt
 *
 */
public interface Subjectable {

	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	void attach(Observerable observer);

	/**
	 * 删除观察者
	 * 
	 * @param observer
	 */
	void detach(Observerable observer);

	/**
	 * 通知所有观察者
	 */
	void info();
}
