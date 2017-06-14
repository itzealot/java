package com.sky.projects.jdk.lambda;

public class FunctionalInterfaceWithException {

	public static void main(String[] args) throws Exception {
		InterfaceWithException target = i -> System.out.println("i=" + i);
		try {
			target.apply(10);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InterfaceWithException targetJ = j -> {
			System.out.println("i=" + j);
			throw new Exception("run error for j");
		};
		targetJ.apply(5);
	}
}
