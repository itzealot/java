package com.sky.projects.jdk.stack;

/**
 * 
 * @author zealot
 *
 * @param <E>
 */
public interface Stack<E> {

	/**
	 * push an element into stack
	 * 
	 * @param e
	 */
	void push(E e);

	/**
	 * pop an element from stack
	 * 
	 * @return
	 */
	E pop();

	/**
	 * peek the stack's top element
	 * 
	 * @return
	 */
	E peek();

	boolean isEmpty();

	int size();
}
