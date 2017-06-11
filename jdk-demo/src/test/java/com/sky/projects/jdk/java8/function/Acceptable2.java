package com.sky.projects.jdk.java8.function;

@FunctionalInterface
public interface Acceptable2<K, V> {

	void accept(K k, V v);

}
