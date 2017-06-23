package com.sky.projects.jdk.lambda;

public class Z {

	public static void main(String[] args) {
		I o = (I & J) () -> {
			System.out.println("aaaaaaa");
		};

		o.foo();
	}
}

@FunctionalInterface
interface I {
	void foo();
}

@FunctionalInterface
interface J {
	void foo();
}

interface G1 {
	<E extends Exception> Object m() throws E;
}

interface G2 {
	<F extends Exception> String m() throws Exception;
}

@FunctionalInterface
interface G extends G1, G2 {
}