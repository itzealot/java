package com.zt.test.design.observer;

/**
 * 具体的观察者，当其监听的状态发生改变时，update方法就会被主题回调，进而可以在观察者内容进行逻辑的处理
 * 
 * @author a
 *
 */
public class ConcreteObserver implements IObserver {

	public void update(Event event) {
		// TODO Auto-generated method stub
		System.out.println("observer receives information");
	}

}
