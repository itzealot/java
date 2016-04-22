package com.sky.projects.jdk.observer;

public class TestObserver {

	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();

		Observerable observerA = new ConcreteObserver();
		subject.attach(observerA);

		Observerable observerB = new ConcreteObserver();
		subject.attach(observerB);

		subject.info();
	}
}
