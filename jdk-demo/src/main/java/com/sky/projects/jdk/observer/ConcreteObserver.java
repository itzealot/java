package com.sky.projects.jdk.observer;

/**
 * 具体的观察者，当其监听的状态发生改变时，update方法就会被主题回调，进而可以在观察者内容进行逻辑的处理
 * 
 * @author zt
 *
 */
public class ConcreteObserver implements Observerable {

	public void update(Event event) {
		System.out.println("observer receives information");
	}

}
