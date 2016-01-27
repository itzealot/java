package com.zt.test.design.observer;

public class TestObserver {
	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();
		IObserver observerA = new ConcreteObserver();
		IObserver observerB = new ConcreteObserver();
		subject.attach(observerA);
		subject.attach(observerB);

		subject.info();
	}
}
