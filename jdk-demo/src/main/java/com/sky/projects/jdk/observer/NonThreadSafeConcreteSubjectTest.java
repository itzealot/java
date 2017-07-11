package com.sky.projects.jdk.observer;

public class NonThreadSafeConcreteSubjectTest {

	public static void main(String[] args) {
		NonThreadSafeConcreteSubject subject = new NonThreadSafeConcreteSubject();

		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				while (true) { // add observer
					subject.attach(new ConcreteObserver());
				}
			}).start();
		}

		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				while (true) { // remove head
					subject.detach();
				}
			}).start();
		}

		for (int i = 0; i < 1; i++) {
			new Thread(() -> {
				while (true) {
					subject.info();
				}
			}).start();
		}
	}
}
