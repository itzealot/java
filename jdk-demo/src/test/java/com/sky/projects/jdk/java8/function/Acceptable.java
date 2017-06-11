package com.sky.projects.jdk.java8.function;

@FunctionalInterface
public interface Acceptable<T> {

	void accept(T t);

}
