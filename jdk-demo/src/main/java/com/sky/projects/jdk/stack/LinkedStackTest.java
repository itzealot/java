package com.sky.projects.jdk.stack;

import junit.framework.TestCase;

public class LinkedStackTest extends TestCase {

	public void testStack() {
		Stack<String> stack = new LinkedStack<>();

		stack.push("a");
		stack.push("b");
		stack.push("b1");
		stack.push("b2");
		stack.pop();
		stack.push("c");

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
