package com.sky.projects.jdk.stack;

import java.util.LinkedList;

/**
 * LinkedStack
 * 
 * @author zealot
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class LinkedStack<E> extends LinkedList<E> implements Stack<E> {

	@Override
	public void push(E e) {
		addFirst(e);
	}

	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public int size() {
		return super.size();
	}

}